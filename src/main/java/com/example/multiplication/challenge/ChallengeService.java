package com.example.multiplication.challenge;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ChallengeService {
    ChallengeAttempt verify(ChallengeAttemptDTO attemptDTO);
    List<ChallengeAttempt> getStatsForAlias(String alias);
}