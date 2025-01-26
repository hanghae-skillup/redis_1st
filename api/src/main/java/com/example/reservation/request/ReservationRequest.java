package com.example.reservation.request;

import lombok.Getter;

import java.util.List;

@Getter
public class ReservationRequest {
    private Long memberId;
    private Long screeningId;
    private List<Long> seatIds;

    public ReservationRequest(Long memberId, Long screeningId, List<Long> seatIds) {
        this.memberId = memberId;
        this.screeningId = screeningId;
        this.seatIds = seatIds;
    }

    private void validate() {
        if (this.memberId == null) {
            throw new IllegalArgumentException("영화 예매시 로그인이 필요합니다.");
        }
        if (this.screeningId == null) {
            throw new IllegalArgumentException("예매할 상영시간을 선택해주세요.");
        }
        if (seatIds.isEmpty() || seatIds.size() > 5) {
            throw new IllegalArgumentException("예매할 좌석을 1개 이상 5개 이하로 선택해주세요.");
        }
    }

    public ReservationServiceRequest toServiceRequest() {
        this.validate();
        return ReservationServiceRequest.builder()
                .memberId(memberId)
                .screeningId(screeningId)
                .seatIds(seatIds)
                .build();
    }
}
