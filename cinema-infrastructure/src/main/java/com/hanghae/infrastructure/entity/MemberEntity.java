package com.hanghae.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name= "member")
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate birthDate; // 생년월일

    @Builder
    public MemberEntity(Long id, LocalDate birthDate, Long createdBy, Long updatedBy) {
        this.id = id;
        this.birthDate = birthDate;
        this.setCreatedBy(createdBy);
        this.setUpdatedBy(updatedBy);
    }

}
