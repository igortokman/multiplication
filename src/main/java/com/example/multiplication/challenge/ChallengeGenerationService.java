package com.example.multiplication.challenge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ChallengeGenerationService {

	public static final int MIN_FACTOR_VALUE = 11;
	public static final int MAX_FACTOR_VALUE = 99;

	private Random random;

	public ChallengeGenerationService() {
		random = new Random();
	}

	public ChallengeGenerationService(Random random) {
		this.random = random;
	}

	private int next() {
		return random.nextInt(MAX_FACTOR_VALUE - MIN_FACTOR_VALUE) + MIN_FACTOR_VALUE;
	}

	public Challenge generate() {
//		return null;
		return new Challenge(next(), next());
	}
}