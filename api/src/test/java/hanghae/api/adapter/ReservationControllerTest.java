package hanghae.api.adapter;

import hanghae.application.dto.request.ReservationRequest;
import hanghae.application.dto.response.ReservationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationControllerTest {

    RestClient restClient = RestClient.create("http://localhost:8081");

    // @Test
    void reserveSeatTest() {
        ReservationResponse response =
                reserve(new ReservationRequest(5L, 5L, 30L, List.of("B1", "B2", "B3")));
        System.out.println("Reservation response: " + response);
    }

    ReservationResponse reserve(ReservationRequest request) {
        return restClient.post()
                .uri("/api/v1/reservation")
                .body(request)
                .retrieve()
                .body(ReservationResponse.class);
    }
}