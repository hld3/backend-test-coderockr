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

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	private CreateUserService createUserService;

	UserController(CreateUserService createUserService) {
		this.createUserService = createUserService;
	}

	@PostMapping("/create")
	public ResponseEntity<UserResponse> createNewUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
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

	private Map<String, String> constructErrors(BindingResult bindingResult) {
		return bindingResult.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
	}
}

