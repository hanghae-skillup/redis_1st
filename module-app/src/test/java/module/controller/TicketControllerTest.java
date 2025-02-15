package module.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.ticket.TicketResponse;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTest {

	private final TicketController ticketController;
	private final MockMvc mockMvc;
	private final ObjectMapper objectMapper;

	@Autowired
	public TicketControllerTest(TicketController ticketController, MockMvc mockMvc, ObjectMapper objectMapper) {
		this.ticketController = ticketController;
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@Test
	@DisplayName("[조회] - 기능")
	public void getAllTicketTest() {
		//given
		Long showingId = 7760L;

		//when
		ResponseEntity<List<TicketResponse>> allTicket = ticketController.getAllTicket(showingId);

		//then
		assertThat(allTicket.getStatusCode().value()).isEqualTo(200);
		assertThat(allTicket.getBody().size()).isEqualTo(25);
	}


	@Test
	@DisplayName("limiter - continuous request with same showing")
	public void 연속두번() throws Exception {
		// given
		// 요청 본문 데이터 생성
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("username", "dbdb1114");
		requestBody.put("showingId", 9501);

		// ticketList 구성
		Map<String, Object> ticket = new HashMap<>();
		ticket.put("ticketId", 58575);
		requestBody.put("ticketList", Collections.singletonList(ticket));

		// json 문자열로 변환
		String jsonRequest = objectMapper.writeValueAsString(requestBody);

		// when
		mockMvc.perform(post("/api/v1/ticket/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
			.andExpect(status().is(HttpStatus.OK.value()));

		// then
		mockMvc.perform(post("/api/v1/ticket/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
			.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}
}
