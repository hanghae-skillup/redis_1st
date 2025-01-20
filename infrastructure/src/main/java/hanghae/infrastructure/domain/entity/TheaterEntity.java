package hanghae.infrastructure.domain.entity;

import hanghae.domain.domain.Theater;
import hanghae.domain.types.theater.ShowtimeCollection;
import hanghae.infrastructure.domain.converter.ShowtimeCollectionConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "theaters")
@NoArgsConstructor
public class TheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theaterId;

    @Setter
    private String theaterName;

    @Convert(converter = ShowtimeCollectionConverter.class)
    private ShowtimeCollection showtimeCollection;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TheaterShowtimeEntity> theaterShowtime = new ArrayList<>();

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieTheaterEntity> movieTheater = new ArrayList<>();

    // TODO mapstruct 사용하자 -> 엔티티에서 정적 팩토리 메서드 사용하니까 이름이 직관적이지 않음
    public static Theater toTheaterDomain(TheaterEntity theaterEntity) {
        return Theater.builder()
                .theaterName(theaterEntity.getTheaterName())
                .showtimeCollection(theaterEntity.getShowtimeCollection())
                .build();
    }
}
