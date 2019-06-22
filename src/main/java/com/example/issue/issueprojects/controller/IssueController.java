package com.example.issue.issueprojects.controller;

import com.example.issue.issueprojects.model.User;
import com.example.issue.issueprojects.model.issue.IssueModel;
import com.example.issue.issueprojects.model.issue.IssueRepo;
import com.example.issue.issueprojects.model.issueUpdate.IssueUpdateRepo;
import com.example.issue.issueprojects.model.issueUpdate.IssueUpdateService;
import com.example.issue.issueprojects.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class IssueController {

    @Autowired
    private IssueRepo issueRepo;

    @Autowired
    IssueUpdateRepo issueUpdateRepo;

    @Autowired
    IssueUpdateService issueUpdateService;


    @Autowired
    private UserService userService;

    private Path path;

    @Autowired
    public void setIssueRepo(IssueRepo issueRepo){
        this.issueRepo = issueRepo;
    }

    @RequestMapping(value = "/issue/issue",method = RequestMethod.GET)
    public String insert(Model model){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("Url", user.getImg());
        model.addAttribute("StaffName",user.getName());
        model.addAttribute("comname",user.comModel.getCom_name());
        model.addAttribute("get_userid",user.getId());
        model.addAttribute("issue", new IssueModel());
        return "/issue/issue";
    }

    //List all issue
    @RequestMapping(value = "/issue/listAll")
    public String listAll(Model model){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("Url", user.getImg());
        model.addAttribute("StaffName",user.getName());
        model.addAttribute("issues",issueUpdateService.findAllAll());
        return "/issue/issueEdit";
    }

    /**
     * Uplaod Issue to DATABASE
     * @param issue
     * @param bindingResult
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/issue/save", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String save(IssueModel issue, BindingResult bindingResult, HttpServletRequest request
                       , @RequestParam("file") MultipartFile file
    ) throws IOException{
        System.out.println(file.getOriginalFilename());

        String uploadPath="/opt/images";
        File path = new File(uploadPath);

        if(!path.exists())
            path.mkdirs();

        System.out.println(uploadPath);

        //Covert File Name to UUID
        String fileName= file.getOriginalFilename();
        fileName = UUID.randomUUID() +"."+fileName.substring(fileName.lastIndexOf(".")+1);

        //Upload file to project path
        Files.copy(file.getInputStream(), Paths.get(uploadPath,fileName));
        String pathName = String.valueOf(Paths.get(uploadPath,fileName ));
        System.out.println(pathName);

        //Upload Path to DATABASE
        issue.setFiles(pathName);

        issueRepo.save(issue);

//        MultipartFile issueImg= issue.getFile();
//        String rooDirec=request.getSession().getServletContext().getRealPath("/");
//        path = Paths.get("E:\\Veasna-Fiplus\\Projects\\Spring\\issueprojects\\src\\main\\resources\\static\\assets\\images\\"
//                + issue.getIssue_no() + file.getOriginalFilename());
//
//        if(issueImg != null && !issueImg.isEmpty()){
//            try {
//                issueImg.transferTo(new File(path.toString()));
//            }catch (IllegalStateException | IOException e){
//                e.printStackTrace();
//                throw new RuntimeException("Saving File was not successful"+e);
//            }
//        }

        return "/issue/issue";
    }

    /**
     * Edit issue using issue_no
     * @param model
     * @param issue_no
     * @return
     */
    @RequestMapping(path = "/issue/edit/{issue_no}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "issue_no") int issue_no) {
        model.addAttribute("issue", issueRepo.findOne(issue_no));
        return "/issue/issue";
    }

    /**
     * Delete
     * @param issue_no
     * @return
     */
    @RequestMapping(path = "/issue/delete/{issue_no}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(name = "issue_no") int issue_no) {
        issueRepo.delete(issue_no);
        return "redirect:/issue/listAll";
    }
    @RequestMapping("/issue/home")
    public String issueHome(){

        return "/issue/index";
    }




}
