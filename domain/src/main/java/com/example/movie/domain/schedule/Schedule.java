package com.example.movie.domain.schedule;

import com.example.movie.domain.common.BaseAggregateRoot;
import com.example.movie.domain.movie.Movie;
import com.example.movie.domain.ticket.Ticket;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Entity
@Comment("상영 시간표")
public class Schedule extends BaseAggregateRoot<Schedule> {

    @ManyToOne
    private Movie movie;

    @Comment("상영 시작 시간")
    private LocalTime startAt;

    @Comment("상영 종료 시간")
    private LocalTime endAt;

    @OneToMany(mappedBy = "schedule")
    private List<Ticket> tickets;

    public Schedule(Movie movie, LocalTime startAt, LocalTime endAt, List<Ticket> tickets) {
        this.movie = movie;
        this.startAt = startAt;
        this.endAt = endAt;
        this.tickets = tickets;
    }
}
