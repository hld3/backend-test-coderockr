package com.dodson.backendcoderockr.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.dodson.backendcoderockr.domain.model.UserModel;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("h2")
public class UserRepositoryIntegrationTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void test_saveUser() {
		UserModel user = new UserModel();
		// TODO make UserModel builder. 
		user.setUserId(UUID.randomUUID());
		user.setLastName("Dodson");
		user.setFirstName("Harry");
		user.setCreationDate(0L);

		userRepository.saveAndFlush(user);
		entityManager.clear(); // force fetching a new instance of the user.

		UserModel savedUser = userRepository.findByUserId(user.getUserId());
		assertNotNull(savedUser);
		assertEquals(savedUser.getUserId(), user.getUserId());
		assertEquals(savedUser.getLastName(), user.getLastName());
		assertEquals(savedUser.getFirstName(), user.getFirstName());
		assertEquals(savedUser.getCreationDate(), user.getCreationDate());
	}
}
