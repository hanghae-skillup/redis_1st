package com.movie.moviestorage.movie.entity;

import com.movie.moviestorage.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "seat")
public class SeatEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    private Long screenId;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL COMMENT '좌석번호'")
    private String seatNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeatEntity seatEntity)) return false;
        return id != null && id.equals(seatEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
