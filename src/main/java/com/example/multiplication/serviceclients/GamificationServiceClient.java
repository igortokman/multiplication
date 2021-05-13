package com.example.multiplication.serviceclients;

import com.example.multiplication.challenge.ChallengeAttempt;
import com.example.multiplication.challenge.ChallengeSolvedDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Service
@Slf4j
public class GamificationServiceClient {
	private final RestTemplate restTemplate;
	private final String hostUrl;

	public GamificationServiceClient(@Value("${service.gamification.host}") String host) {

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(20000);
		requestFactory.setConnectionRequestTimeout(20000);
		requestFactory.setReadTimeout(30000);

		restTemplate = new RestTemplate(requestFactory);
		hostUrl = host;
	}

	public boolean sendAttempt(ChallengeAttempt attempt) {
		try {

			ChallengeSolvedDTO solvedDTO = new ChallengeSolvedDTO(attempt.getUser().getId(),
					attempt.getId(),
					attempt.getFactorA(),
					attempt.getFactorB(),
					attempt.isCorrect());
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(hostUrl + "/attempts", solvedDTO, String.class);
			log.info("gamification service response: {}", responseEntity);

			return responseEntity.getStatusCode().is2xxSuccessful();
		} catch (Exception e) {
			log.info("there was a problem", e);
			return false;
		}
	}
}