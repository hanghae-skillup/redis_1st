package com.hanghae.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "screen")
public class ScreenEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id")
    private Long id;

    @Column
    private String screenName; //상영관 이름

    @Builder
    public ScreenEntity(Long id, String screenName, Long createdBy, Long updatedBy) {
        this.id = id;
        this.screenName = screenName;
        this.setCreatedBy(createdBy);
        this.setUpdatedBy(updatedBy);
    }
}
