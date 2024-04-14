package com.dodson.backendcoderockr.service;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dodson.backendcoderockr.domain.dto.UserDTO;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.repository.UserRepository;

public class CreateUserServiceUnitTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private CreateUserService createUserService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_whenCreatingUser_thenUserIsSaved() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(UUID.randomUUID());
		userDTO.setFirstName("Harry");
		userDTO.setLastName("Dodson");
		userDTO.setCreationDate(123L);

		createUserService.createNewUser(userDTO);

		ArgumentCaptor<UserModel> userCaptor = ArgumentCaptor.forClass(UserModel.class);
		verify(userRepository).save(userCaptor.capture());
		UserModel savedUser = userCaptor.getValue();

		assertNotNull(savedUser);
		assertEquals("Harry", savedUser.getFirstName());
		assertEquals("Dodson", savedUser.getLastName());
		assertEquals(123L, savedUser.getCreationDate());
	}
}