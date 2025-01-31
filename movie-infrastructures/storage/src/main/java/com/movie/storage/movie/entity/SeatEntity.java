package com.movie.storage.movie.entity;

import com.movie.common.enums.AxisY;
import com.movie.storage.BaseEntity;
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

    @Column(columnDefinition = "VARCHAR(10) NOT NULL COMMENT '좌석번호'")
    private String seatNumber;

    @Column(columnDefinition = "INT NOT NULL COMMENT 'X 좌표'")
    private Integer axisX;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(1) NOT NULL COMMENT 'Y 좌표'")
    private AxisY axisY;

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
