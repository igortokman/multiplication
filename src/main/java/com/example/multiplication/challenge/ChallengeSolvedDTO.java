package com.example.multiplication.challenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ChallengeSolvedDTO {
	private Long userId;
	private Long attemptId;

	private int factorA;
	private int factorB;
	private boolean correct;
}
