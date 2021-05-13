package com.example.multiplication.user;

import lombok.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String alias;

	public User(String alias) {
		this(null, alias);
	}
}