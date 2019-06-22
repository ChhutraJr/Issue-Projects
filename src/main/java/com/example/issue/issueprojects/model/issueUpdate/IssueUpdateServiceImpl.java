package com.example.issue.issueprojects.model.issueUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueUpdateServiceImpl implements IssueUpdateService{

    @Autowired
    private IssueUpdateRepo issueUpdateRepo;


    @Override
    public List<IssueUpdateModel> findAllAll() {
        return issueUpdateRepo.findAlls();
    }

    @Override
    public List<IssueUpdateModel> listAll() {
        return issueUpdateRepo.listAllUp();
    }
}
