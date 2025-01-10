package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.TestDomain;
import org.example.service.TestService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping("/checkSave")
    public void save(@RequestBody @Validated TestDomain testDomain) {
        testService.save(testDomain);
    }
}
