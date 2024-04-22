package com.dodson.backendcoderockr.domain.dto.user;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class UserDTO {

	@NotNull
	@JsonProperty
	private UUID userId;

	@JsonProperty
	private String firstName;

	@JsonProperty
	private String lastName;

	@JsonProperty
	private long creationDate;

	public UserDTO() {}

	public UserDTO(UUID userId, String firstName, String lastName, long creationDate) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creationDate = creationDate;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}
}
