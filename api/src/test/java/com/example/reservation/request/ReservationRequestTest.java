package com.example.reservation.request;

import com.example.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationRequestTest {

    @Test
    @DisplayName("serviceRequest를 생성할때 memberId가 없으면 예외가 던져진다")
    void memberId_exception() {
        ReservationRequest reservationRequest = new ReservationRequest(null, null, null);
        assertThatThrownBy(reservationRequest::toServiceRequest)
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("로그인이 필요합니다.");
    }

    @Test
    @DisplayName("serviceRequest를 생성할때 screeningId가 없으면 예외가 던져진다")
    void screeningId_exception() {
        ReservationRequest reservationRequest = new ReservationRequest(1L, null, null);
        assertThatThrownBy(reservationRequest::toServiceRequest)
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("예매할 상영시간을 선택해주세요.");
    }

    @Test
    @DisplayName("serviceRequest를 생성할때 seats가 비어있으면 예외가 던져진다")
    void seatIds_exception() {
        ReservationRequest reservationRequest = new ReservationRequest(1L, 1L, List.of());
        assertThatThrownBy(reservationRequest::toServiceRequest)
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("예매할 좌석을 선택해주세요");
    }

    @Test
    @DisplayName("예외 조건을 모두 만족하면 ReservationServieRequest를 생성한다.")
    void success() {
        ReservationRequest reservationRequest = new ReservationRequest(1L, 1L, List.of(1L, 2L));
        ReservationServiceRequest serviceRequest = reservationRequest.toServiceRequest();

        assertThat(serviceRequest.getMemberId()).isEqualTo(1L);
        assertThat(serviceRequest.getScreeningId()).isEqualTo(1L);
        assertThat(serviceRequest.getSeatIds()).hasSize(2);
    }
}