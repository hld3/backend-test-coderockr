package com.dodson.backendcoderockr.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
         * @param bindingResult Spring created. Will populate found validation errors.
         * @return the {@link UserResponse} result.
         */
        @PostMapping("/create")
        public final ResponseEntity<UserResponse> createNewUser(@Valid @RequestBody final UserDTO userDTO,
                        final BindingResult bindingResult) {
                UserResponse response = new UserResponse();
                if (bindingResult.hasErrors()) {
                        Map<String, String> errors = constructErrors(bindingResult);
                        response.setErrors(errors);
                        logger.error("There was an error in the create user request: " + errors);
                        return ResponseEntity.badRequest().body(response);
                }
                // TODO handle if createNewUser fails.
                response.setUserResult(createUserService.createNewUser(userDTO));
                return ResponseEntity.ok(response);
        }

        private Map<String, String> constructErrors(final BindingResult bindingResult) {
                return bindingResult.getFieldErrors()
                                .stream()
                                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        }
}
