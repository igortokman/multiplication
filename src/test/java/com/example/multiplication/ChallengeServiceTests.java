package com.example.multiplication;

import com.example.multiplication.challenge.*;
import com.example.multiplication.serviceclients.GamificationServiceClient;
import com.example.multiplication.user.User;
import com.example.multiplication.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChallengeServiceTests {
	private ChallengeService service;

	@Mock
	private UserRepository userRepository;

	@Mock
	private ChallengeAttemptRepository attemptRepository;

	@Mock
	private GamificationServiceClient serviceClient;

	@BeforeEach
	public void setUp() {
		service = new ChallengeServiceImpl(attemptRepository, userRepository, serviceClient);
		given(attemptRepository.save(any())).will(returnsFirstArg());
		given(serviceClient.sendAttempt(any())).willReturn(true);
	}

	@Test
	public void checkCorrectAttempt() {
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(30, 20, "john", 600);
		ChallengeAttempt attempt = service.verify(attemptDTO);
		then(attempt.isCorrect()).isTrue();
	}

	@Test
	public void checkWrongAttempt() {
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(30, 20, "john", 500);
		ChallengeAttempt attempt = service.verify(attemptDTO);
		then(attempt.isCorrect()).isFalse();
	}

	@Test
	public void checkCorrectAttemptTests() {
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(30, 50, "tom", 1500);

		ChallengeAttempt attempt = service.verify(attemptDTO);


		then(attempt.isCorrect()).isTrue();
		verify(userRepository).save(new User("tom"));
		verify(attemptRepository).save(attempt);
	}

	@Test
	public void checkExistingUserTests() {
		User user = new User(1L, "tom");
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(30, 50, "tom", 1500);
		given(userRepository.findByAlias("tom")).willReturn(Optional.of(user));

		ChallengeAttempt attempt = service.verify(attemptDTO);

		then(attempt.isCorrect()).isTrue();
		verify(userRepository, never()).save(any());
		verify(attemptRepository).save(attempt);
	}
}
