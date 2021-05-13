package com.example.multiplication.challenge;

import com.example.multiplication.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Setter
@Getter
@AllArgsConstructor
public class ChallengeAttempt {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	@JsonIgnore
	private User user;
	private int factorA;
	private int factorB;
	private int result;
	private boolean correct;
}