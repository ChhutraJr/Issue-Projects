package com.example.issue.issueprojects.controller;

import com.example.issue.issueprojects.model.maintenance.MaintenanceModel;
import com.example.issue.issueprojects.model.maintenance.MaintenannceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MaintenanceController {

    private MaintenannceRepo maintenannceRepo;

    @Autowired
    public void setIssueRepo(MaintenannceRepo maintenannceRepo) {
        this.maintenannceRepo = maintenannceRepo;
    }

    @RequestMapping(value = "/maintenance",method = RequestMethod.GET)
    public ModelAndView insert(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/maintenance/maintenance");
        return modelAndView;
    }

    @RequestMapping(value = "/saveMaintenance",method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("maintenance_id") String maintenance_id,
                             @RequestParam("com_code") int com_code,
                             @RequestParam("service_tag") String service_tag,
                             @RequestParam("service_date") String service_date,
                             @RequestParam("expire_date") String expire_date,
                             @RequestParam("alert_new_b4") String alert_new_b4){
        ModelAndView modelAndView = new ModelAndView("redirect:/maintenance");
        MaintenanceModel maintenanceModel;

        if(!maintenance_id.isEmpty()){
            maintenanceModel=(MaintenanceModel)maintenannceRepo.findOne(Integer.parseInt(maintenance_id));
        }else {
            maintenanceModel=new MaintenanceModel();
        }
        maintenanceModel.setCom_code(com_code);
        maintenanceModel.setService_tag(service_tag);
        maintenanceModel.setService_date(service_date);
        maintenanceModel.setExpire_date(expire_date);
        maintenanceModel.setAlert_new_b4(alert_new_b4);
        maintenannceRepo.save(maintenanceModel);

        return modelAndView;
    }
}
