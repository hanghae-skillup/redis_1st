package com.bmsnc.adapter.out.persistence;

import com.bmsnc.adapter.out.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SeatInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatInfoId;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    private String seatPosition;

}
