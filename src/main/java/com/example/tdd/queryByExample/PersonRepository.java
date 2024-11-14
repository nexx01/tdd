package com.example.tdd.queryByExample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PersonRepository extends JpaRepository<Person,Long>, QueryByExampleExecutor<Person> {

}
