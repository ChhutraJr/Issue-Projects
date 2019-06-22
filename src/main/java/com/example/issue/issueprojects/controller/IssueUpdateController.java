package com.example.issue.issueprojects.controller;

import com.example.issue.issueprojects.model.User;
import com.example.issue.issueprojects.model.issueUpdate.IssueUpdateModel;
import com.example.issue.issueprojects.model.issueUpdate.IssueUpdateRepo;
import com.example.issue.issueprojects.model.issueUpdate.IssueUpdateService;
import com.example.issue.issueprojects.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IssueUpdateController {

    @Autowired
    private IssueUpdateRepo issueUpdateRepo;

    @Autowired
    private IssueUpdateService issueUpdateService;

    @Autowired
    private UserService userService;

    @Autowired
    public void setIssueUpdateRepo(IssueUpdateRepo issueUpdateRepo) {
        this.issueUpdateRepo = issueUpdateRepo;
    }

    //List all Issue
    @RequestMapping(path = "/issues/update",method = RequestMethod.GET)
    public String insert(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("StaffName",user.getName());
        model.addAttribute("issues",issueUpdateService.findAllAll());
        model.addAttribute("issueUp",issueUpdateService.listAll());
        model.addAttribute("issueUpdate", new IssueUpdateModel());
        return "/issue/issueUpdate";
    }

    // Insert
    @RequestMapping(path = "/issues/save", method = RequestMethod.POST)
    public String save(IssueUpdateModel issueUpdateModel, BindingResult bindingResult){
        issueUpdateRepo.save(issueUpdateModel);
        return "/issues/issueUpdate";
    }


    @RequestMapping("/issues/home")
    public String issueHome(){

        return "/issue/index";
    }

//    @RequestMapping("/issues/listUpdate")
//    public String listAll(Model model){
//        model.addAttribute("issueUp",issueUpdateService.findAllAll());
//        return "/issue/listIssueUpdate";
//    }

}
