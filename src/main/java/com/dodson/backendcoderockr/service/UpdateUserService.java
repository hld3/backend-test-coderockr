package com.dodson.backendcoderockr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodson.backendcoderockr.domain.dto.UserDTO;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.repository.UserRepository;

@Service
public class UpdateUserService {

	@Autowired
	private UserRepository userRepository;

	public UpdateUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void updateUser(UserDTO userDTO) {
		UserModel toUpdate = userRepository.findByUserId(userDTO.getUserId());
		if (toUpdate != null) {
			toUpdate.setFirstName(userDTO.getFirstName());
			toUpdate.setLastName(userDTO.getLastName());
			userRepository.save(toUpdate);
		} else {
			// if for some reason the user doesn't exist, save it with the given creation date.
			UserModel newUser = new UserModel();
			newUser.setUserId(userDTO.getUserId());
			newUser.setFirstName(userDTO.getFirstName());
			newUser.setLastName(userDTO.getLastName());
			newUser.setCreationDate(userDTO.getCreationDate());
			userRepository.save(newUser);
		}
	}
}
