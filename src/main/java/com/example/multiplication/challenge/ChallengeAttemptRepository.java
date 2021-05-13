package com.example.multiplication.challenge;

import com.example.multiplication.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long> {
	List<ChallengeAttempt> findTop10ByUserAliasOrderByIdDesc(String userAlias);
}