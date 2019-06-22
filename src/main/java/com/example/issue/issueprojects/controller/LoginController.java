package com.example.issue.issueprojects.controller;



import com.example.issue.issueprojects.model.User;
import com.example.issue.issueprojects.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Controller
public class LoginController {



    @Autowired
    private UserService userService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.getPrincipal().equals("anonymousUser")) {
            System.out.print(auth.getPrincipal());
            modelAndView.setViewName("/admin/index");
        }else{
            System.out.print(auth.getPrincipal());
            modelAndView.setViewName("security/login");
        }
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("security/registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult
            , @RequestParam("file") MultipartFile file) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("security/registration");
        } else {

            System.out.println(file.getOriginalFilename());
            String uploadPath="/opt/images";
            File path = new File(uploadPath);

            if(!path.exists())
                path.mkdirs();

            System.out.println(uploadPath);
            //Covert File Name to UUID Random name
            String fileName= file.getOriginalFilename();
            fileName = UUID.randomUUID() +"."+fileName.substring(fileName.lastIndexOf(".")+1);

            //Upload file to project path
            Files.copy(file.getInputStream(), Paths.get(uploadPath,fileName));

            //Upload path to Database
            String pathName = String.valueOf(Paths.get(uploadPath,fileName));
            System.out.println(pathName);

            user.setImg(pathName);
            userService.saveUser(user);

            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("security/registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("email", user.getEmail());
        modelAndView.addObject("userName", user.getName());
        modelAndView.addObject("Url", user.getImg());
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.addObject("userid",user.getId());
        modelAndView.setViewName("/admin/index");
        return modelAndView;
    }
    @RequestMapping("/issue/company")
    public ModelAndView company(){
        ModelAndView modelAndView = new ModelAndView("/issue/company");
        return modelAndView;
    }

    @RequestMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth !=null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "security/login";
    }
    @RequestMapping("/error/403")
    public String error403(){
        return "/error/403";
    }



}
