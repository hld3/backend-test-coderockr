package com.dodson.backendcoderockr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.dto.user.UserDTOBuilder;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.domain.model.UserModelBuilder;
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
		UserDTO userDTO = new UserDTOBuilder().build();
		UserModel userModel = new UserModelBuilder().build();
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
		UserDTO userDTO = new UserDTOBuilder().build();
		when(userRepository.findByUserId(userDTO.getUserId())).thenReturn(null);

		deleteUserService.deleteUser(userDTO);

		verify(userRepository, never()).delete(null);
	}
}
