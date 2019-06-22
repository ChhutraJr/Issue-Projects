package com.example.issue.issueprojects.model.maintenance;

import javax.persistence.*;

@Entity
@Table(name = "tbl_maintenance")
public class MaintenanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int maintenance_id;

    @Column(name = "com_code")
    private int com_code;

    @Column(name = "service_tag")
    private String service_tag;

    @Column(name = "service_date")
    private String service_date;

    @Column(name = "expire_date")
    private String expire_date;

    @Column(name = "alert_new_b4")
    private String alert_new_b4;

    public MaintenanceModel(){

    }

    public int getMaintenance_id() {
        return maintenance_id;
    }

    public void setMaintenance_id(int maintenance_id) {
        this.maintenance_id = maintenance_id;
    }

    public int getCom_code() {
        return com_code;
    }

    public void setCom_code(int com_code) {
        this.com_code = com_code;
    }

    public String getService_tag() {
        return service_tag;
    }

    public void setService_tag(String service_tag) {
        this.service_tag = service_tag;
    }

    public String getService_date() {
        return service_date;
    }

    public void setService_date(String service_date) {
        this.service_date = service_date;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public String getAlert_new_b4() {
        return alert_new_b4;
    }

    public void setAlert_new_b4(String alert_new_b4) {
        this.alert_new_b4 = alert_new_b4;
    }
}
