package com.example.movie.domain.ticket;

import static com.google.common.base.Preconditions.checkArgument;
import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;

import com.example.movie.common.domain.BaseAggregateRoot;
import com.example.movie.domain.schedule.Schedule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
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
@Table(indexes = {
    @Index(name = "idx_ticket_schedule_id", columnList = "schedule_id")
})
@Entity
@Comment("티켓")
public class Ticket extends BaseAggregateRoot<Ticket> {

    @ManyToOne(optional = false)
    @JoinColumn(name = "schedule_id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Schedule schedule;

    @Enumerated(EnumType.STRING)
    @Comment("좌석 열")
    @Column(nullable = false)
    private SeatsColType seatsCol;

    @Enumerated(EnumType.STRING)
    @Comment("좌석 행")
    @Column(nullable = false)
    private SeatsRowType seatsRow;

    public Ticket(Schedule schedule, SeatsColType seatsCol, SeatsRowType seatsRow) {
        checkArgument(schedule != null, "schedule must be provided.");
        checkArgument(seatsCol != null, "seatsCol must be provided.");
        checkArgument(seatsRow != null, "seatsRow must be provided.");

        this.schedule = schedule;
        this.seatsCol = seatsCol;
        this.seatsRow = seatsRow;
    }
}
