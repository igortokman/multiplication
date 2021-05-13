package com.example.multiplication.challenge;

import com.example.multiplication.serviceclients.GamificationServiceClient;
import com.example.multiplication.user.User;
import com.example.multiplication.user.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeAttemptRepository attemptRepository;
    private final UserRepository userRepository;
    private final GamificationServiceClient serviceClient;

    @Override
    public ChallengeAttempt verify(ChallengeAttemptDTO attemptDTO) {
        int result = attemptDTO.getFactorA() * attemptDTO.getFactorB();
        boolean correct = result == attemptDTO.getGuess();
        String alias = attemptDTO.getUserAlias();
        User user = userRepository.findByAlias(alias).orElseGet(() -> userRepository.save(new User(alias)));

        log.info("attemptDTO: {}", attemptDTO);
        log.info("found user: {}", user);

        ChallengeAttempt attempt = new ChallengeAttempt(
                null,
                user,
                attemptDTO.getFactorA(),
                attemptDTO.getFactorB(),
                attemptDTO.getGuess(),
                correct);

        ChallengeAttempt challengeAttempt = attemptRepository.save(attempt);
        serviceClient.sendAttempt(challengeAttempt);
        return challengeAttempt;
    }

    @Override
    public List<ChallengeAttempt> getStatsForAlias(String alias) {
        return attemptRepository.findTop10ByUserAliasOrderByIdDesc(alias);
    }
}