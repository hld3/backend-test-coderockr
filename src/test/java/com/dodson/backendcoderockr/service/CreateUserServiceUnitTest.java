package com.dodson.backendcoderockr.service;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dodson.backendcoderockr.domain.dto.UserDTO;
import com.dodson.backendcoderockr.domain.dto.UserDTOBuilder;
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
		UserDTO userDTO = new UserDTOBuilder().build();

		createUserService.createNewUser(userDTO);

		ArgumentCaptor<UserModel> userCaptor = ArgumentCaptor.forClass(UserModel.class);
		verify(userRepository).save(userCaptor.capture());
		UserModel savedUser = userCaptor.getValue();

		assertNotNull(savedUser);
		assertEquals(userDTO.getFirstName(), savedUser.getFirstName());
		assertEquals(userDTO.getLastName(), savedUser.getLastName());
		assertEquals(userDTO.getCreationDate(), savedUser.getCreationDate());
	}
}
