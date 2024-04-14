package com.dodson.backendcoderockr.service;

import org.springframework.stereotype.Service;

import com.dodson.backendcoderockr.domain.dto.UserDTO;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.repository.UserRepository;

@Service
public class CreateUserService {

	private UserRepository userRepository;

	CreateUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void createNewUser(UserDTO userDTO) {
		UserModel userModel = new UserModel();
		userModel.setUserId(userDTO.getUserId());
		userModel.setFirstName(userDTO.getFirstName());
		userModel.setLastName(userDTO.getLastName());
		userModel.setCreationDate(userDTO.getCreationDate());

		userRepository.save(userModel);
	}
}
