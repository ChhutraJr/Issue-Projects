package com.example.issue.issueprojects.model.issueUpdate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IssueUpdateRepo extends JpaRepository<IssueUpdateModel,Integer> {

//    String query = "select a.issue_no,a.user_id, a.date, a.phone, a.purpose, isnull(a.issue_type,''),isnull(a.descriptionof_issue,'') descriptionof_issue, a.status, a.remark, isnull(a.files,'') as files,\n" +
//        " isnull(b.issueupdate_id,'') as issueupdate_id, isnull(b.status_do,'') as status_do, isnull(b.staff_name,'') staff_name, isnull(b.description,'') as description, b.close_date\n" +
//        "from tbl_issues a LEFT join tbl_issuesupdate b on a.issue_no=b.issue_no";
    String query = "{call issue_report01}";
    @Query(value = query,nativeQuery = true)
    List<IssueUpdateModel> findAlls();


    //    String query = "select a.issue_no,a.user_id, a.date, a.phone, a.purpose, isnull(a.issue_type,''),isnull(a.descriptionof_issue,'') descriptionof_issue, a.status, a.remark, isnull(a.files,'') as files,\n" +
    //        " isnull(b.issueupdate_id,'') as issueupdate_id, isnull(b.status_do,'') as status_do, isnull(b.staff_name,'') staff_name, isnull(b.description,'') as description, b.close_date\n" +
    //        "from tbl_issues a join tbl_issuesupdate b on a.issue_no=b.issue_no";
        String query1 = "{call issue_report}";
    @Query(value = query1,nativeQuery = true)
    List<IssueUpdateModel> listAllUp();

}
