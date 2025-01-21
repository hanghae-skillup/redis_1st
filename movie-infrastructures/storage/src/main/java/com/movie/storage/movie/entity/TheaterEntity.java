package com.movie.storage.movie.entity;

import com.movie.storage.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "theater")
public class TheaterEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL COMMENT '극장명'")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TheaterEntity theaterEntity)) return false;
        return id != null && id.equals(theaterEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
