package com.example.issue.issueprojects.model.issueUpdate;




import com.example.issue.issueprojects.model.issue.IssueModel;

import javax.persistence.*;

@Entity
@Table(name = "tbl_issuesupdate")
public class IssueUpdateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int issueupdate_id;

    @Column(name = "issue_no")
    private int issue_no;

    @Column(name="staff_name")
    private String staff_name;

    @Column(name="description")
    private String description;

    @Column(name="close_date")
    private String close_date;

    @Column(name="status_do")
    private String status_do;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_no",insertable = false, updatable = false)
    public IssueModel issueModel;

    public IssueUpdateModel(){

    }

    public IssueUpdateModel(IssueModel issueModel){
        this.issueModel=issueModel;
    }

    public int getIssueupdate_id() {
        return issueupdate_id;
    }

    public IssueModel getIssueModel() {
        return issueModel;
    }

    public void setIssueModel(IssueModel issueModel) {
        this.issueModel = issueModel;
    }

    public int getIssueppdate_id() {
        return issueupdate_id;
    }

    public void setIssueupdate_id(int issueupdate_id) {
        this.issueupdate_id = issueupdate_id;
    }


    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String issueimplement) {
        this.staff_name = issueimplement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }


    public String getStatus_do() {
        return status_do;
    }

    public void setStatus_do(String status_do) {
        this.status_do = status_do;
    }

    public int getIssue_no() {
        return issue_no;
    }

    public void setIssue_no(int issue_no) {
        this.issue_no = issue_no;
    }
}
