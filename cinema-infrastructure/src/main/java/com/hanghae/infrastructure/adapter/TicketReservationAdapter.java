package com.hanghae.infrastructure.adapter;

import com.hanghae.application.port.out.TicketReservationRepositoryPort;
import com.hanghae.domain.model.TicketReservation;
import com.hanghae.infrastructure.entity.TicketReservationEntity;
import com.hanghae.infrastructure.mapper.TicketReservationMapper;
import com.hanghae.infrastructure.repository.TicketReservationRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TicketReservationAdapter implements TicketReservationRepositoryPort {
    private final TicketReservationRepositoryJpa repository;

    @Override
    public List<TicketReservation> saveMovieReservations(List<TicketReservation> ticketReservations) {
        //도메인을 엔티티로 변환
        List<TicketReservationEntity> ticketReservationEntity = ticketReservations.stream().map(TicketReservationMapper::toEntity).collect(Collectors.toList());

        //예약정보 저장
        List<TicketReservationEntity> savedEntitys = repository.saveAll(ticketReservationEntity);
        
        //엔티티를 도메인으로 변환하여 리턴
        return savedEntitys.stream().map(TicketReservationMapper::toDomain).collect(Collectors.toList());
    }
}
