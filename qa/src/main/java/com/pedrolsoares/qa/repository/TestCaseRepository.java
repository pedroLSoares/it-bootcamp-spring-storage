package com.pedrolsoares.qa.repository;

import com.pedrolsoares.qa.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

    public List<TestCase> findAllByLastUpdate(Date lastUpdate);
}
