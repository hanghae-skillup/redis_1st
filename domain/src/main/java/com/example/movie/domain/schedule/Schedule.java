package com.example.movie.domain.schedule;

import static com.google.common.base.Preconditions.checkArgument;
import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;

import com.example.movie.common.domain.BaseAggregateRoot;
import com.example.movie.domain.movie.Movie;
import com.example.movie.domain.ticket.Ticket;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
    @Index(name = "idx_schedule_movie_id", columnList = "movie_id")
})
@Entity
@Comment("상영 시간표")
public class Schedule extends BaseAggregateRoot<Schedule> {

    @ManyToOne
    @JoinColumn(name = "movie_id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Movie movie;

    @Comment("상영 시작 시간")
    @Column(nullable = false)
    private LocalTime startAt;

    @Comment("상영 종료 시간")
    @Column(nullable = false)
    private LocalTime endAt;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.DETACH)
    private List<Ticket> tickets = new ArrayList<>();

    public Schedule(Movie movie, LocalTime startAt, LocalTime endAt) {
        checkArgument(movie != null, "movie must be provided.");
        checkArgument(startAt != null, "startAt must be provided.");
        checkArgument(endAt != null, "endAt must be provided.");
        checkArgument(startAt.isAfter(endAt), "startAt must be before endAt.");

        this.movie = movie;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
