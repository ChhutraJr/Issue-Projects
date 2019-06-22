package com.example.issue.issueprojects.model.issue;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepo extends JpaRepository<IssueModel,Integer> {
}
