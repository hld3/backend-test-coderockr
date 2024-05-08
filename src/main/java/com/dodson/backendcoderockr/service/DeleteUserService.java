package com.dodson.backendcoderockr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.dto.user.status.UserResult;
import com.dodson.backendcoderockr.domain.dto.user.status.UserStatus;
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
     * @return the {@UserResult}.
     */
    public UserResult deleteUser(final UserDTO userDTO) {
        UserModel userModel = userRepository.findByUserId(userDTO.getUserId());
        UserResult result = new UserResult();

        if (userModel != null) {
            userRepository.delete(userModel);
            result.setMessage("User deleted successfully");
        } else {
            logger.info("User was not found for deletion: " + userDTO.getUserId());
            result.setMessage("User was not found for deletion");
        }

        result.setUserDTO(userDTO);
        result.setUserStatus(UserStatus.DELETED);
        return result;
    }
}
