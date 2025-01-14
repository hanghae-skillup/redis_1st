package com.bmsnc.adapter.out.persistence;

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
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "movie_theater_info_id")
    private MovieTheaterInfo movieTheaterInfo;
}
