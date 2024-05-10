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
public class UpdateUserService {

    /**
     * Logger for the service.
     */
    private Logger logger = LoggerFactory.getLogger(UpdateUserService.class);

    /**
     * Repository to connect to the database.
     */
    private UserRepository userRepository;

    public UpdateUserService(final UserRepository theUserRepository) {
        this.userRepository = theUserRepository;
    }

    /**
     * Updates the user. A new user is created if the user in the DTO does not
     * exist.
     * @param userDTO the user data to update the user with.
     * @return the {@UserResult}.
     */
    public UserResult updateUser(final UserDTO userDTO) {
        UserModel toUpdate = userRepository.findByUserId(userDTO.getUserId());
        UserResult result = new UserResult();

        if (toUpdate != null) {
            toUpdate.setFirstName(userDTO.getFirstName());
            toUpdate.setLastName(userDTO.getLastName());
            userRepository.save(toUpdate);
            result.setMessage("User was updated successfully: " + userDTO.getUserId());
        } else {
            logger.info("Could not update, the user does not exist. Creating new user: " + userDTO.getUserId());
            UserModel newUser = new UserModel();
            newUser.setUserId(userDTO.getUserId());
            newUser.setFirstName(userDTO.getFirstName());
            newUser.setLastName(userDTO.getLastName());
            newUser.setCreationDate(userDTO.getCreationDate());
            userRepository.save(newUser);
            result.setMessage("User did not exist, a new user was created: " + userDTO.getUserId());
        }

        result.setUserDTO(userDTO);
        result.setUserStatus(UserStatus.UPDATED);
        return result;
    }
}
