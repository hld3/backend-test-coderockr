package com.dodson.backendcoderockr.domain.dto.user.status;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResult {

	@JsonProperty
	private UserStatus userStatus = UserStatus.NONE;

	@JsonProperty
	private UserDTO userDTO;

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
}
