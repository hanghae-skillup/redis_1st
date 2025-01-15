package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.TestDomain;
import org.example.repository.TestRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public void save(TestDomain testDomain) {
        testRepository.save(testDomain);
    }
}
