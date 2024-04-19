package com.dodson.backendcoderockr.domain.response;

import java.util.Map;

import com.dodson.backendcoderockr.domain.dto.user.status.UserResult;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {

	@JsonProperty
	private String message;

	@JsonProperty
	private UserResult userResult;

	// TODO Should probably make an error object to return a List of.
	@JsonProperty
	private Map<String, String> errors;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserResult getUserResult() {
		return userResult;
	}

	public void setUserResult(UserResult userResult) {
		this.userResult = userResult;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
}
