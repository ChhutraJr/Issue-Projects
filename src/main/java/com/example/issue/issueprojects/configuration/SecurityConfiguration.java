package com.example.issue.issueprojects.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AccessDeniedHandler accessDeniedHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private UserDetailsService customUserDetailsService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    public SecurityConfiguration(
            @Qualifier("customSuccessHandler") AuthenticationSuccessHandler authenticationSuccessHandler
            ,@Qualifier("customAccessDeniedHandler") AccessDeniedHandler accessDeniedHandler
            ,@Qualifier("customFailureHandler") AuthenticationFailureHandler authenticationFailureHandler
    ){
        this.authenticationSuccessHandler= authenticationSuccessHandler;
        this.accessDeniedHandler=accessDeniedHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);

        auth
                .userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin/**","/company/**").hasAuthority("ADMIN")
                .antMatchers("/home").hasAnyAuthority("DEV","USER","ADMIN")
                .antMatchers("/maintenance").hasAnyAuthority("DEV","ADMIN")
                .antMatchers("/issues/**").hasAnyAuthority("DEV","ADMIN")
                .antMatchers("/issue/**").hasAnyAuthority("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login")
                    .failureUrl("/login?error=true")
                    .usernameParameter("email")
                    .passwordParameter("password").permitAll()
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler    )
                .and()
                    .logout().permitAll();
//                .csrf().disable()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error=true")
//                .usernameParameter("email")
//                .passwordParameter("password")
//
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/").and().exceptionHandling()
//                .accessDeniedPage("/error/403");

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**","/ajax/**","/css/**"
                        ,"/data/**","/fonts/**","/img/**","/js/**","/php/**","/sound/**","/xml.gmap/**"
                        ,"E:\\Veasna-Fiplus\\Projects\\Spring\\issueprojects\\src\\main\\resources\\static\\img\\**"

                );
    }

}
