package com.pedrolsoares.qa.service;

import com.pedrolsoares.qa.model.TestCase;
import com.pedrolsoares.qa.repository.TestCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;

    public TestCase saveTest(TestCase testCase) {
        return testCaseRepository.save(testCase);
    }

    public List<TestCase> listTestCase() {
        return testCaseRepository.findAll();
    }

    public List<TestCase> listTestCase(Date dateFilter) {
        return testCaseRepository.findAllByLastUpdate(dateFilter);
    }

    public TestCase findTestById(Long id) {
        Optional<TestCase> testCaseOptional = testCaseRepository.findById(id);

        return testCaseOptional.get();
    }

    public TestCase removeTestCase(Long id) {
        TestCase testCase = findTestById(id);

        testCaseRepository.deleteById(id);

        return testCase;
    }
}
