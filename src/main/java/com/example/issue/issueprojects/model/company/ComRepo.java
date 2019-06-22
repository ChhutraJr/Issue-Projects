package com.example.issue.issueprojects.model.company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComRepo extends CrudRepository<ComModel,Integer> {

}
