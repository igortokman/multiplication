package com.example.multiplication.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByAlias(String alias);
}