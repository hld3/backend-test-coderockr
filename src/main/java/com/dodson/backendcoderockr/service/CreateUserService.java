package com.dodson.backendcoderockr.service;

import org.springframework.stereotype.Service;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.dto.user.status.UserResult;
import com.dodson.backendcoderockr.domain.dto.user.status.UserStatus;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.repository.UserRepository;

@Service
public class CreateUserService {

	private UserRepository userRepository;

	CreateUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserResult createNewUser(UserDTO userDTO) {
		UserModel userModel = new UserModel();
		userModel.setUserId(userDTO.getUserId());
		userModel.setFirstName(userDTO.getFirstName());
		userModel.setLastName(userDTO.getLastName());
		userModel.setCreationDate(userDTO.getCreationDate());
		userRepository.save(userModel);

		UserResult result = new UserResult();
		result.setUserDTO(userDTO);
		result.setUserStatus(UserStatus.CREATED);
		return result;
	}
}
