package com.dodson.backendcoderockr.service;

import org.springframework.stereotype.Service;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.dto.user.status.UserResult;
import com.dodson.backendcoderockr.domain.dto.user.status.UserStatus;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.repository.UserRepository;

@Service
public class CreateUserService {

        /**
         * Repository to save users to.
         */
        private UserRepository userRepository;

        CreateUserService(final UserRepository theUserRepository) {
                this.userRepository = theUserRepository;
        }

        /**
         * Saves a user to the database.
         * @param userDTO the user DTO with the user data.
         * @return the {@link UserResult} with the user and creation status.
         */
        public final UserResult createNewUser(final UserDTO userDTO) {
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
