package com.dodson.backendcoderockr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
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
import com.dodson.backendcoderockr.repository.UserRepository;

public class DeleteUserServiceUnitTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private DeleteUserService deleteUserService;

	@BeforeEach
	private void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_whenUserExists_thenUserIsDeleted() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(UUID.randomUUID());
		userDTO.setFirstName("Harry");
		userDTO.setLastName("Dodson");
		userDTO.setCreationDate(123L);

		UserModel userModel = new UserModel();
		userModel.setUserId(UUID.randomUUID());
		userModel.setFirstName("Wrong");
		userModel.setLastName("Name");
		userModel.setCreationDate(321L);
		when(userRepository.findByUserId(userDTO.getUserId())).thenReturn(userModel);

		deleteUserService.deleteUser(userDTO);

		ArgumentCaptor<UserModel> userCaptor = ArgumentCaptor.forClass(UserModel.class);
		verify(userRepository).delete(userCaptor.capture());
		UserModel deletedUser = userCaptor.getValue();

		assertEquals(userModel.getUserId(), deletedUser.getUserId());
		assertEquals(userModel.getFirstName(), deletedUser.getFirstName());
		assertEquals(userModel.getLastName(), deletedUser.getLastName());
		assertEquals(userModel.getCreationDate(), deletedUser.getCreationDate());
	}

	@Test
	void test_whenUserDoesNotExist_thenNothing() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(UUID.randomUUID());
		userDTO.setFirstName("Harry");
		userDTO.setLastName("Dodson");
		userDTO.setCreationDate(123L);
		when(userRepository.findByUserId(userDTO.getUserId())).thenReturn(null);

		deleteUserService.deleteUser(userDTO);

		verify(userRepository, never()).delete(null);
	}
}
