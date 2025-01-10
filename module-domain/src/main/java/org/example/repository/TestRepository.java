package org.example.repository;

import org.example.domain.TestDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestDomain, Long> {

}
