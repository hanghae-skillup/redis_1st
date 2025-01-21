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
@Table(name = "screen")
public class ScreenEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    private Long theaterId;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL COMMENT '싱영관 명'")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScreenEntity screenEntity)) return false;
        return id != null && id.equals(screenEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
