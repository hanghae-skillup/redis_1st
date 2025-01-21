package com.movie.storage.movie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationComplexIds {

    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long scheduleId;
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long seatId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof ReservationComplexIds ids)) return false;
        return Objects.equals(scheduleId, ids.scheduleId) && Objects.equals(seatId, ids.seatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId, seatId);
    }

    public static ReservationComplexIds of(Long scheduleId, Long seatId) {
        return ReservationComplexIds.builder()
                .scheduleId(scheduleId)
                .seatId(seatId)
                .build();
    }

}
