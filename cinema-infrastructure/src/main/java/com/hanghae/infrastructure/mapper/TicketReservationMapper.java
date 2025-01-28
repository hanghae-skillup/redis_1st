package com.hanghae.infrastructure.mapper;

import com.hanghae.domain.model.TicketReservation;
import com.hanghae.infrastructure.entity.TicketReservationEntity;

public class TicketReservationMapper {
    public static TicketReservation toDomain(TicketReservationEntity ticketReservationEntity) {
        return new TicketReservation(
                ticketReservationEntity.getId(),
                ticketReservationEntity.getScreenSeat(),
                ScreeningScheduleMapper.toDomain(ticketReservationEntity.getScreeningScheduleEntity()),
                MemberMapper.toDomain(ticketReservationEntity.getMemberEntity()),
                ticketReservationEntity.getCreatedBy(),
                ticketReservationEntity.getCreatedAt(),
                ticketReservationEntity.getUpdatedBy(),
                ticketReservationEntity.getUpdatedAt()
        );
    }

    public static TicketReservationEntity toEntity(TicketReservation ticketReservation) {
        return TicketReservationEntity.builder()
                .id(ticketReservation.getTicketId())
                .screenSeat(ticketReservation.getScreenSeat())
                .screeningScheduleEntity(ScreeningScheduleMapper.toEntity(ticketReservation.getScreeningSchedule()))
                .memberEntity(MemberMapper.toEntity(ticketReservation.getMember()))
                .createdBy(ticketReservation.getCreatedBy())
                .updatedBy(ticketReservation.getUpdatedBy())
                .build();
    }
}
