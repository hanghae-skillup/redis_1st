package com.example.movie.domain.ticket;

import com.example.movie.domain.common.BaseAggregateRoot;
import com.example.movie.domain.schedule.Schedule;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Comment("티켓")
public class Ticket extends BaseAggregateRoot<Ticket> {

    @ManyToOne
    private Schedule schedule;

    @Enumerated(EnumType.STRING)
    @Comment("좌석 열")
    private SeatsColType seatsCol;

    @Enumerated(EnumType.STRING)
    @Comment("좌석 행")
    private SeatsRowType seatsRow;

    public Ticket(Schedule schedule, SeatsColType seatsCol, SeatsRowType seatsRow) {
        this.schedule = schedule;
        this.seatsCol = seatsCol;
        this.seatsRow = seatsRow;
    }
}
