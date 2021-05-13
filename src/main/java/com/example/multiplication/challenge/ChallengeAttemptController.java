package com.example.multiplication.challenge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/attempts")
public class ChallengeAttemptController {
	private final ChallengeService service;

	@PostMapping
	ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDTO attemptDTO) {
		return ResponseEntity.ok(service.verify(attemptDTO));
	}

	@GetMapping
	ResponseEntity<List<ChallengeAttempt>> getStatistics(@RequestParam String alias) {
		return ResponseEntity.ok(service.getStatsForAlias(alias));
	}
}