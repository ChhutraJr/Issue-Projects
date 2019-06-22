package com.example.issue.issueprojects.controller;

import com.example.issue.issueprojects.model.User;
import com.example.issue.issueprojects.model.UserRepo;
import com.example.issue.issueprojects.model.company.ComModel;
import com.example.issue.issueprojects.model.company.ComRepo;
import com.example.issue.issueprojects.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Arrays;


@Controller
public class CompanyContorller {

    @Autowired
    private UserService userService;

    private ComRepo comRepo;
    private UserRepo userRepo;

    public CompanyContorller(ComRepo comRepo,UserRepo userRepo){
        this.comRepo=comRepo;
        this.userRepo=userRepo;
    }

    @RequestMapping("/company")
    public ModelAndView com(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView modelAndView = new ModelAndView("/company/company");
        modelAndView.addObject("user_id",user.getId());
        modelAndView.addObject("lists",userService.listAlls());
        modelAndView.addObject("COM",comRepo.findAll());


        return modelAndView;
    }
    @RequestMapping("/company/insert")
    public ModelAndView insert(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        ModelAndView modelAndView = new ModelAndView("/company/insert");
        modelAndView.addObject("UserName",user.getName());
        modelAndView.addObject("user_id",user.getId());
        return modelAndView;
    }
    @RequestMapping( value = "/save", method = RequestMethod.POST)
    public ModelAndView doSave(@RequestParam("com_code") String com_code,
                               @RequestParam("com_name") String com_name,
                               @RequestParam("com_type") String com_type){
        ModelAndView mv = new ModelAndView("redirect:/company");
        ComModel com;
        if(!com_code.isEmpty()){
            com =(ComModel)comRepo.findOne(Integer.parseInt(com_code));
        } else {
            com = new ComModel();
        }
        com.setCom_name(com_name);
        com.setCom_type(com_type);
        comRepo.save(com);
        return mv;
    }
    @RequestMapping(value = "/delete/{com_code}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("com_code") int id){
        ModelAndView mv = new ModelAndView("redirect:/company");
        comRepo.delete(id);
        return mv;
    }
    @RequestMapping(value = "/edit/{com_code}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("com_code") int id){
        ModelAndView mv = new ModelAndView("/company/edit");
        mv.addObject("lists",comRepo.findOne(id));
        return mv;
    }
}
