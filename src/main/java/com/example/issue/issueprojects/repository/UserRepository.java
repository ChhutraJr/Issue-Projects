package com.example.issue.issueprojects.repository;



import com.example.issue.issueprojects.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    String q ="SELECT * FROM tbl_user a JOIN tbl_company b on a.com_code=b.com_code";
    @Query(value = q,nativeQuery = true)
    public List<User> listAlls();

    User findByEmail(String email);
}
