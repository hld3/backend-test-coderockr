package com.dodson.backendcoderockr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dodson.backendcoderockr.domain.dto.UserDTO;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.domain.model.UserModelBuilder;
import com.dodson.backendcoderockr.repository.UserRepository;

public class UpdateUserServiceUnitTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UpdateUserService updateUserService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_whenTheUserExists_thenFirstAndLastNameUpdate() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(UUID.randomUUID());
		userDTO.setFirstName("Harry");
		userDTO.setLastName("Dodson");
		userDTO.setCreationDate(123L);

		UserModel userModel = new UserModelBuilder().withUserId(userDTO.getUserId()).build();
		when(userRepository.findByUserId(userDTO.getUserId())).thenReturn(userModel);

		updateUserService.updateUser(userDTO);

		ArgumentCaptor<UserModel> userCaptor = ArgumentCaptor.forClass(UserModel.class);
		verify(userRepository).save(userCaptor.capture());
		UserModel savedUser = userCaptor.getValue();

		assertNotNull(savedUser);
		assertEquals(userDTO.getFirstName(), savedUser.getFirstName());
		assertEquals(userDTO.getLastName(), savedUser.getLastName());
		assertEquals(userModel.getCreationDate(), savedUser.getCreationDate()); // doesn't update from model.
	}

	@Test
	void test_whenTheUserIsMissing_thenUserIsCreated() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(UUID.randomUUID());
		userDTO.setFirstName("Harry");
		userDTO.setLastName("Dodson");
		userDTO.setCreationDate(123L);

		updateUserService.updateUser(userDTO);

		ArgumentCaptor<UserModel> userCaptor = ArgumentCaptor.forClass(UserModel.class);
		verify(userRepository).save(userCaptor.capture());
		UserModel savedUser = userCaptor.getValue();

		assertNotNull(savedUser);
		assertEquals(userDTO.getUserId(), savedUser.getUserId());
		assertEquals(userDTO.getFirstName(), savedUser.getFirstName());
		assertEquals(userDTO.getLastName(), savedUser.getLastName());
		assertEquals(userDTO.getCreationDate(), savedUser.getCreationDate());
	}
}
