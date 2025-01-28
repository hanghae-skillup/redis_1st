package com.hanghae.infrastructure.entity;

import com.hanghae.domain.model.enums.ScreenSeat;
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
@Table(name= "ticket_reservation")
public class TicketReservationEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) //Enum을 문자열("A1", "A2")로 저장하도록 설정
    private ScreenSeat screenSeat; // 상영관 좌석

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private ScreeningScheduleEntity screeningScheduleEntity; // 상영시간표

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity; // 회원

    @Builder
    public TicketReservationEntity(Long id, ScreenSeat screenSeat, ScreeningScheduleEntity screeningScheduleEntity, MemberEntity memberEntity, Long createdBy, Long updatedBy) {
        this.id = id;
        this.screenSeat = screenSeat;
        this.screeningScheduleEntity = screeningScheduleEntity;
        this.memberEntity = memberEntity;
        this.setCreatedBy(createdBy);
        this.setUpdatedBy(updatedBy);
    }
}
