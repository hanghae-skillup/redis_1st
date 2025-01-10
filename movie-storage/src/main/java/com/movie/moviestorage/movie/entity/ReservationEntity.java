package com.movie.moviestorage.movie.entity;

import com.movie.moviestorage.BaseEntity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation")
public class ReservationEntity extends BaseEntity {

    @EmbeddedId
    private ReservationComplexIds id;

}
