package com.example.issue.issueprojects.model.issue;

import com.example.issue.issueprojects.model.User;
import com.example.issue.issueprojects.model.issueUpdate.IssueUpdateModel;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_issues")
public class IssueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int issue_no;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "date")
    private String date;

    @Column(name = "phone")
    private String phone;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "issue_type")
    private String issue_type;

    @Column(name = "descriptionof_issue")
    private String description;

    @Column(name = "remark")
    private String remark;

    @Column(name = "status")
    private String status;

    @Column(name="files")
    private String  files;

    @Transient
    private MultipartFile file;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "issueModel")
    public Set<IssueUpdateModel> issueUpdateModelSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",insertable = false, updatable = false)
    public User user;


    public IssueModel(){
    }

    public IssueModel(String purpose,User user){
        this.purpose=purpose;
        this.user=user;
    }

    public Set<IssueUpdateModel> getIssueUpdateModelSet() {
        return issueUpdateModelSet;
    }

    public void setIssueUpdateModelSet(Set<IssueUpdateModel> issueUpdateModelSet) {
        this.issueUpdateModelSet = issueUpdateModelSet;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


    public int getIssue_no() {
        return issue_no;
    }

    public void setIssue_no(int issue_no) {
        this.issue_no = issue_no;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String issues) {
        this.purpose = issues;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssue_type() {
        return issue_type;
    }

    public void setIssue_type(String issue_type) {
        this.issue_type = issue_type;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
