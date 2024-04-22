package com.dodson.backendcoderockr.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.dto.user.UserDTOBuilder;
import com.dodson.backendcoderockr.service.CreateUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerUnitTest {

	private ObjectMapper om = new ObjectMapper();
	private MockMvc mockMvc;

	@Mock
	private CreateUserService createUserService;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	void test_whenCreateUserEndpointIsHit_thenTheUserIsCreated() throws Exception {
		UserDTO userDTO = new UserDTOBuilder().build();

		mockMvc.perform(post("/user/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(userDTO)))
				.andExpect(status().isOk());
	}
}
