package com.example.issue.issueprojects.model.company;


import com.example.issue.issueprojects.model.User;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_company")
public class ComModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "com_code")
    public int com_code;

    @Column(name = "com_name")
    public String com_name;

    @Column(name = "com_type")
    public String com_type;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "comModel")
    public Set<User> userSet;

    public ComModel() {
    }

    public ComModel(String com_name, String com_type,int com_code) {
        this.com_code = com_code;
        this.com_name = com_name;
        this.com_type = com_type;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public int getCom_code() {
        return com_code;
    }

    public void setCom_code(int com_code) {
        this.com_code = com_code;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getCom_type() {
        return com_type;
    }

    public void setCom_type(String com_type) {
        this.com_type = com_type;
    }
}
