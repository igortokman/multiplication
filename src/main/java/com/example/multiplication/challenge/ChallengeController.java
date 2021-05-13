package com.example.multiplication.challenge;

import com.example.multiplication.user.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/challenge")
public class ChallengeController {
	private final ChallengeGenerationService service;

	@GetMapping("/random")
	public Challenge random() {
		Challenge challenge = service.generate();
		log.info("Random challenge was generated: {}", challenge);
		return challenge;
	}
}