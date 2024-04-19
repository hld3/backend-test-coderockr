package com.dodson.backendcoderockr.service;

import org.springframework.stereotype.Service;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.repository.UserRepository;

@Service
public class DeleteUserService {

	private UserRepository userRepository;

	public DeleteUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void deleteUser(UserDTO userDTO) {
		UserModel userModel = userRepository.findByUserId(userDTO.getUserId());
		if (userModel != null) {
			userRepository.delete(userModel);
		}
	}
}
