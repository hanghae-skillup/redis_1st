package module.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ShowingControllerTest {

	private final MockMvc mockMvc;

	@Autowired
	public ShowingControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	public void 의존성주입() {
		assertThat(mockMvc).isNotNull();
	}

	@Test
	public void 리미터_51번째_조회() throws Exception {
		String reqURI = "/api/v1/showings/all?title=말할 수 없는 비밀&genreId=2";
		for (int i = 0; i < 50; i++) {
			mockMvc.perform(get(reqURI))
				.andExpect(status().is(HttpStatus.OK.value()));
			Thread.sleep(500);
		}
		mockMvc.perform(get(reqURI))
			.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	public void 리미터_초당3회() throws Exception {
		String reqURI = "/api/v1/showings/all?title=말할 수 없는 비밀&genreId=2";
		IntStream.range(0,2).parallel()
			.forEach(i->{
				try {
					mockMvc.perform(get(reqURI))
						.andExpect(status().is(HttpStatus.OK.value()));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
		mockMvc.perform(get(reqURI))
			.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}
}
