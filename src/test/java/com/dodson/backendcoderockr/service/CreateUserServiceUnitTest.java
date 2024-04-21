package com.dodson.backendcoderockr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.dto.user.UserDTOBuilder;
import com.dodson.backendcoderockr.domain.dto.user.status.UserResult;
import com.dodson.backendcoderockr.domain.dto.user.status.UserStatus;
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
		UserDTO userDTO = new UserDTOBuilder().build();
		UserResult result = createUserService.createNewUser(userDTO);

		UserDTO savedUser = result.getUserDTO();
		assertNotNull(savedUser);
		assertEquals(userDTO.getFirstName(), savedUser.getFirstName());
		assertEquals(userDTO.getLastName(), savedUser.getLastName());
		assertEquals(userDTO.getCreationDate(), savedUser.getCreationDate());
		assertEquals(UserStatus.CREATED, result.getUserStatus());
	}
}
