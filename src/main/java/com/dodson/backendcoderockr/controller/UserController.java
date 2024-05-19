package com.dodson.backendcoderockr.controller;

import com.dodson.backendcoderockr.service.CreateUserService;
import com.dodson.backendcoderockr.service.UpdateUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.response.UserResponse;
import com.dodson.backendcoderockr.service.DeleteUserService;

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

    /**
     * The service to update a user in the database.
     */
    private UpdateUserService updateUserService;

    /**
     * The service to delete a user in the database.
     */
    private DeleteUserService deleteUserService;

    UserController(final CreateUserService theCreateUserService, final UpdateUserService theUpdateUserService,
                   final DeleteUserService theDeleteUserService) {
        this.createUserService = theCreateUserService;
        this.updateUserService = theUpdateUserService;
        this.deleteUserService = theDeleteUserService;
    }

    /**
     * Method to construct a new user.
     * @param userDTO the user DTO from the request body.
     * @return the {@link UserResponse} result.
     */
    @PostMapping("/create")
    public final ResponseEntity<UserResponse> createNewUser(@Valid @RequestBody final UserDTO userDTO) {
        logger.info("Creating new user: " + userDTO.getUserId());
        UserResponse response = new UserResponse();
        response.setUserResult(createUserService.createNewUser(userDTO));
        logger.info("New user created: " + userDTO.getUserId());
        return ResponseEntity.ok(response);
    }

    /**
     * Method to update an existing user.
     * @param userDTO the user DTO from the request body.
     * @return the {@link UserResponse} result.
     */
    @PostMapping("/update")
    public final ResponseEntity<UserResponse> updateUser(@Valid @RequestBody final UserDTO userDTO) {
        logger.info("Updating user: " + userDTO.getUserId());
        UserResponse response = new UserResponse();
        response.setUserResult(updateUserService.updateUser(userDTO));
        logger.info("User updated: " + userDTO.getUserId());
        return ResponseEntity.ok(response);
    }


    /**
     * Method to delete an existing user.
     * @param userDTO the user DTO from the request body.
     * @return the {@link UserResponse} result.
     */
    @DeleteMapping("/delete")
    public final ResponseEntity<UserResponse> deleteUser(@Valid @RequestBody final UserDTO userDTO) {
        logger.info("Deleting user: " + userDTO.getUserId());
        UserResponse response = new UserResponse();
        response.setUserResult(deleteUserService.deleteUser(userDTO));
        logger.info("User deleted: " + userDTO.getUserId());
        return ResponseEntity.ok(response);
    }
}
