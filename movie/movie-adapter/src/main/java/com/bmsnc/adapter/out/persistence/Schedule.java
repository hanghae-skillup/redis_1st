package com.bmsnc.adapter.out.persistence;

import com.bmsnc.adapter.out.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    @ManyToOne
    @JoinColumn(name = "movie_theater_info_id")
    private MovieTheaterInfo movieTheaterInfo;
    private LocalDateTime startAt;

}
