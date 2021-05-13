package com.example.multiplication;

import com.example.multiplication.challenge.Challenge;
import com.example.multiplication.challenge.ChallengeGenerationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ChallengeGenerationServiceTests {
	@Spy
	private Random random;
	private ChallengeGenerationService service;

	@BeforeEach
	public void setUp() {
		service = new ChallengeGenerationService(random);
	}

	@Test
	void generateRandomFactor() {
		int delta = ChallengeGenerationService.MAX_FACTOR_VALUE - ChallengeGenerationService.MIN_FACTOR_VALUE;
		given(random.nextInt(delta)).willReturn(20, 30);

		Challenge challenge = service.generate();

		then(challenge).isEqualTo(new Challenge(31, 41));
	}

}
