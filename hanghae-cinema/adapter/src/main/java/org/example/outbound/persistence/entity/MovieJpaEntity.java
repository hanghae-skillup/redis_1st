package org.example.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.enums.AgeRating;
import org.example.enums.Genre;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "movies")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieJpaEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private LocalDate releaseDate;

    @Column(nullable = false)
    private Integer runtimeMinutes;

    @Enumerated(value = EnumType.STRING)
    private AgeRating ageRating;

    @Column(length = 500)
    private String thumbnailUrl;

}
