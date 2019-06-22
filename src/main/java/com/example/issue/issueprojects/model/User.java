package com.example.issue.issueprojects.model;

import com.example.issue.issueprojects.model.company.ComModel;
import com.example.issue.issueprojects.model.issue.IssueModel;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "com_code",nullable = true)
    private int com_code;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name")
    private String last_name;

    @Column(name = "active")
    private int active;

    @Column(name = "img")
    private String img;

    @Transient
    private MultipartFile file;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tbluser_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "com_code",insertable = false, updatable = false)
    public ComModel comModel;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    public Set<IssueModel> issueModelSet;




    public User(ComModel comModel){
        this.comModel=comModel;
    }
    public User(){

    }
    public User(User user) {
        this.active=user.getActive();
        this.last_name=user.getLast_name();
        this.name=user.getName();
        this.password=user.getPassword();
        this.email=user.getEmail();
        this.user_id=user.getId();
        this.roles=user.getRoles();
    }

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String Last_name) {
        this.last_name = Last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCom_code() {
        return com_code;
    }

    public void setCom_code(int com_code) {
        this.com_code = com_code;
    }

    public ComModel getComModel() {
        return comModel;
    }

    public void setComModel(ComModel comModel) {
        this.comModel = comModel;
    }

    public Set<IssueModel> getIssueModelSet() {
        return issueModelSet;
    }

    public void setIssueModelSet(Set<IssueModel> issueModelSet) {
        this.issueModelSet = issueModelSet;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
