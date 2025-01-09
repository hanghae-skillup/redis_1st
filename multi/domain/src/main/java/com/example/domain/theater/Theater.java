package com.example.domain.theater;

import com.example.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Theaters")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Theater extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private Long id;

    @Column(name = "theater_name")
    private String theaterName;


}
