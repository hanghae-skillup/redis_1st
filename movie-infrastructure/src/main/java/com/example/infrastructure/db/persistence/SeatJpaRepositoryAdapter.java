package com.example.infrastructure.db.persistence;

import com.example.application.port.out.SeatRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SeatJpaRepositoryAdapter implements SeatRepositoryPort {
}
