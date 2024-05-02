package com.dodson.backendcoderockr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.response.UserResponse;
import com.dodson.backendcoderockr.service.CreateUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

        /**
         * Logger for the class.
         */
        private Logger logger = LoggerFactory.getLogger(UserController.class);

        /**
         * The service to create a user to save to the database.
         */
        private CreateUserService createUserService;

        UserController(final CreateUserService theCreateUserService) {
                this.createUserService = theCreateUserService;
        }

        /**
         * Method to construct a new user.
         * @param userDTO the user DTO from the request body.
         * @return the {@link UserResponse} result.
         */
        @PostMapping("/create")
        public final ResponseEntity<UserResponse> createNewUser(@Valid @RequestBody final UserDTO userDTO) {
                logger.info("Creating new user: " + userDTO);
                UserResponse response = new UserResponse();
                response.setUserResult(createUserService.createNewUser(userDTO));
                return ResponseEntity.ok(response);
        }
}
