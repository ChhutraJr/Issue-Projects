package com.example.issue.issueprojects.service;



import com.example.issue.issueprojects.model.User;

import java.util.List;

public interface UserService {

    public User findUserByEmail(String email);
    public void saveUser(User userModel);

    public List<User> listAlls();
}
