package com.example.multiplication.challenge;

import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Value
public class ChallengeAttemptDTO {
    @Min(11)
    @Max(99)
    int factorA, factorB;
    String userAlias;
    @Positive
    int guess;
}