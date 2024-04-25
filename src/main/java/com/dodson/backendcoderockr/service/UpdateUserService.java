package com.dodson.backendcoderockr.service;

import org.springframework.stereotype.Service;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.repository.UserRepository;

@Service
public class UpdateUserService {

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
         */
        public void updateUser(final UserDTO userDTO) {
                UserModel toUpdate = userRepository.findByUserId(userDTO.getUserId());
                if (toUpdate != null) {
                        toUpdate.setFirstName(userDTO.getFirstName());
                        toUpdate.setLastName(userDTO.getLastName());
                        userRepository.save(toUpdate);
                } else {
                        UserModel newUser = new UserModel();
                        newUser.setUserId(userDTO.getUserId());
                        newUser.setFirstName(userDTO.getFirstName());
                        newUser.setLastName(userDTO.getLastName());
                        newUser.setCreationDate(userDTO.getCreationDate());
                        userRepository.save(newUser);
                }
        }
}
