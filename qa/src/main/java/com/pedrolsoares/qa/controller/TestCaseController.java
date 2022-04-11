package com.pedrolsoares.qa.controller;

import com.pedrolsoares.qa.dto.TestCaseDTO;
import com.pedrolsoares.qa.dto.TestCaseListItemDTO;
import com.pedrolsoares.qa.model.TestCase;
import com.pedrolsoares.qa.service.TestCaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping("/api/testcases")
@AllArgsConstructor
public class TestCaseController {

    private final TestCaseService testCaseService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody TestCaseDTO testCase, UriComponentsBuilder uriBuilder) {
        TestCase created = testCaseService.saveTest(testCase.dtoToModel());

        URI uri = uriBuilder.path("/{id}").buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<TestCaseListItemDTO>> listTestCases(UriComponentsBuilder uriBuilder) {
        List<TestCase> testCases = testCaseService.listTestCase();

        return ResponseEntity.ok(testCases.stream().map(t -> TestCaseListItemDTO.modelToDTO(t, uriBuilder)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestCase> findById(@PathVariable Long id) {
        TestCase testCase = testCaseService.findTestById(id);

        return ResponseEntity.ok(testCase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestCase> updateById(@PathVariable Long id, @RequestBody TestCaseDTO testCase) {

        TestCase updated = testCaseService.saveTest(testCase.dtoToModel(id));

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TestCase> deleteById(@PathVariable Long id) {
        TestCase testCase = testCaseService.removeTestCase(id);

        return ResponseEntity.ok(testCase);
    }

    @GetMapping
    public ResponseEntity<List<TestCaseListItemDTO>> findByDate(@RequestParam Date last_update, UriComponentsBuilder uriBuilder) {
        List<TestCase> testCases = testCaseService.listTestCase(last_update);

        return ResponseEntity.ok(testCases.stream().map(t -> TestCaseListItemDTO.modelToDTO(t, uriBuilder)).collect(Collectors.toList()));
    }


}
