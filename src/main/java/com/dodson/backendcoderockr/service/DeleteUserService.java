package com.dodson.backendcoderockr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.repository.UserRepository;

@Service
public class DeleteUserService {

    /**
     * Logger for the service.
     */
    private Logger logger = LoggerFactory.getLogger(DeleteUserService.class);

    /**
     * Repository to connect to the database.
     */
    private UserRepository userRepository;

    public DeleteUserService(final UserRepository theUserRepository) {
        this.userRepository = theUserRepository;
    }

    /**
     * Deletes the user.
     * @param userDTO the user to delete.
     */
    public void deleteUser(final UserDTO userDTO) {
        UserModel userModel = userRepository.findByUserId(userDTO.getUserId());
        if (userModel != null) {
            userRepository.delete(userModel);
        } else {
            logger.info("User not found for deletion: " + userDTO.getUserId());
        }
    }
}
