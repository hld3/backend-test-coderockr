package com.dodson.backendcoderockr.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.dto.user.UserDTOBuilder;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.domain.model.UserModelBuilder;
import com.dodson.backendcoderockr.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserRepository userRepository;

    @Test
    void test_whenSendingValidUser_thenUserIsCreated() throws Exception {
        UserDTO userDTO = new UserDTOBuilder().build();

        mockMvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(userDTO)))
                .andExpect(status().isOk());

        UserModel result = userRepository.findByUserId(userDTO.getUserId());

        assertNotNull(result);
        assertEquals(result.getFirstName(), userDTO.getFirstName());
        assertEquals(result.getLastName(), userDTO.getLastName());
        assertEquals(result.getCreationDate(), userDTO.getCreationDate());
    }

    @Test
    void test_whenSendingValidUser_thenUserIsUpdated() throws Exception {
        UserDTO originalDTO = new UserDTOBuilder().build();
        UserModel saveModel = new UserModelBuilder().withAllFields(originalDTO.getUserId(), originalDTO.getFirstName(),
                originalDTO.getLastName(), originalDTO.getCreationDate()).build();
        userRepository.saveAndFlush(saveModel);

        UserDTO updated = new UserDTOBuilder().withUserId(originalDTO.getUserId()).build();

        mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(updated)))
                .andExpect(status().isOk());

        UserModel result = userRepository.findByUserId(updated.getUserId());

        assertNotNull(result);
        assertEquals(result.getFirstName(), updated.getFirstName());
        assertEquals(result.getLastName(), updated.getLastName());
        assertEquals(result.getCreationDate(), originalDTO.getCreationDate());
    }

    @Test
    void test_whenUserDoesNotExist_thenUserIsCreated() throws Exception {
        UserDTO updated = new UserDTOBuilder().build();

        mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(updated)))
                .andExpect(status().isOk());

        UserModel result = userRepository.findByUserId(updated.getUserId());

        assertNotNull(result);
        assertEquals(result.getFirstName(), updated.getFirstName());
        assertEquals(result.getLastName(), updated.getLastName());
        assertEquals(result.getCreationDate(), updated.getCreationDate());
    }
}
