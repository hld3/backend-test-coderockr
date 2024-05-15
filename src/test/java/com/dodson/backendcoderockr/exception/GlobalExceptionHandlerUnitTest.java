package com.dodson.backendcoderockr.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dodson.backendcoderockr.controller.UserController;
import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.dto.user.UserDTOBuilder;
import com.dodson.backendcoderockr.service.CreateUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GlobalExceptionHandlerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private CreateUserService createUserService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void test_requestValidationError() throws Exception {
        UserDTO userDTO = new UserDTOBuilder().build();
        userDTO.setUserId(null);

        mockMvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"userId\":\"must not be null\"}"));
    }
}
