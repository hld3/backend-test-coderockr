package com.dodson.backendcoderockr.domain.dto.user;

import java.util.UUID;

import com.github.javafaker.Faker;

public class UserDTOBuilder {

	private Faker faker = new Faker();

	private UUID userId = UUID.randomUUID();
	private String firstName = faker.name().firstName();
	private String lastName = faker.name().lastName();
	private long creationDate = faker.number().randomNumber(13, true);

	public UserDTO build() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userId);
		userDTO.setFirstName(firstName);
		userDTO.setLastName(lastName);
		userDTO.setCreationDate(creationDate);
		return userDTO;
	}
}
