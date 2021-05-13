package com.example.multiplication;

import com.example.multiplication.challenge.*;
import com.example.multiplication.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ChallengeAttemptController.class)
class ChallengeAttemptControllerTests {
	@MockBean
	private ChallengeService service;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<ChallengeAttemptDTO> jsonRequestAttempt;

	@Autowired
	private JacksonTester<ChallengeAttempt> jsonResultAttempt;

	@Test
	public void postValidResult() throws Exception {
		// given
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(30, 50, "tom", 1500);
		String requestAttemptJson = jsonRequestAttempt.write(attemptDTO).getJson();

		ChallengeAttempt attempt = new ChallengeAttempt(null, new User(null, "tom"), 30, 50, 1500, true);
		String resultAttemptJson = jsonResultAttempt.write(attempt).getJson();

		given(service.verify(eq(attemptDTO))).willReturn(attempt);

		// when
		MockHttpServletResponse response = mvc.perform(post("/attempts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestAttemptJson))
				.andReturn().getResponse();

		// then
		then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		then(response.getContentAsString()).isEqualTo(resultAttemptJson);
	}

	@Test
	public void postInvalidResult() throws Exception {
		// given
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(30, -50, "tom", 1500);
		String requestAttemptJson = jsonRequestAttempt.write(attemptDTO).getJson();


		// when
		MockHttpServletResponse response = mvc.perform(post("/attempts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestAttemptJson))
				.andReturn().getResponse();

		// then
		then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
}
