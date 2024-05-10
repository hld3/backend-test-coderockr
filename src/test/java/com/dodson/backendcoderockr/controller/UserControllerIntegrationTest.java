package com.dodson.backendcoderockr.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.dodson.backendcoderockr.domain.dto.user.UserDTOBuilder;
import com.dodson.backendcoderockr.domain.dto.user.status.UserResult;
import com.dodson.backendcoderockr.domain.dto.user.status.UserStatus;
import com.dodson.backendcoderockr.domain.model.UserModel;
import com.dodson.backendcoderockr.domain.model.UserModelBuilder;
import com.dodson.backendcoderockr.domain.response.UserResponse;
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
        UserDTO newUserDTO = new UserDTOBuilder().build();

        MvcResult mvcResult = mockMvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(newUserDTO)))
                .andExpect(status().isOk())
                .andReturn();

        UserModel createResult = userRepository.findByUserId(newUserDTO.getUserId());
        assertNotNull(createResult);
        assertEquals(createResult.getFirstName(), newUserDTO.getFirstName());
        assertEquals(createResult.getLastName(), newUserDTO.getLastName());
        assertEquals(createResult.getCreationDate(), newUserDTO.getCreationDate());

        UserResponse userResponse = om.readValue(mvcResult.getResponse().getContentAsString(), UserResponse.class);
        UserResult userResult = userResponse.getUserResult();
        assertEquals(userResult.getUserStatus(), UserStatus.CREATED);
        assertEquals(userResult.getUserDTO(), newUserDTO);
        assertEquals(userResult.getMessage(), "User was created: " + newUserDTO.getUserId());
    }

    @Test
    void test_whenSendingValidUser_thenUserIsUpdated() throws Exception {
        UserDTO originalDTO = new UserDTOBuilder().build();
        UserModel saveModel = new UserModelBuilder().withAllFields(originalDTO.getUserId(), originalDTO.getFirstName(),
                originalDTO.getLastName(), originalDTO.getCreationDate()).build();
        userRepository.saveAndFlush(saveModel);

        UserDTO updateDTO = new UserDTOBuilder().withUserId(originalDTO.getUserId()).build();

        MvcResult mvcResult = mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andReturn();

        UserModel userModel = userRepository.findByUserId(updateDTO.getUserId());
        assertNotNull(userModel);
        assertEquals(userModel.getFirstName(), updateDTO.getFirstName());
        assertEquals(userModel.getLastName(), updateDTO.getLastName());
        assertEquals(userModel.getCreationDate(), originalDTO.getCreationDate());

        UserResponse userResponse = om.readValue(mvcResult.getResponse().getContentAsString(), UserResponse.class);
        UserResult userResult = userResponse.getUserResult();
        assertEquals(userResult.getUserStatus(), UserStatus.UPDATED);
        assertEquals(userResult.getUserDTO(), updateDTO);
        assertEquals(userResult.getMessage(), "User was updated successfully: " + updateDTO.getUserId());
    }

    @Test
    void test_whenUserDoesNotExist_thenUserIsCreated() throws Exception {
        UserDTO updateDTO = new UserDTOBuilder().build();

        MvcResult mvcResult = mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andReturn();

        UserModel userModelResult = userRepository.findByUserId(updateDTO.getUserId());
        assertNotNull(userModelResult);
        assertEquals(userModelResult.getFirstName(), updateDTO.getFirstName());
        assertEquals(userModelResult.getLastName(), updateDTO.getLastName());
        assertEquals(userModelResult.getCreationDate(), updateDTO.getCreationDate());

        UserResponse userResponse = om.readValue(mvcResult.getResponse().getContentAsString(), UserResponse.class);
        UserResult userResult = userResponse.getUserResult();
        assertEquals(userResult.getUserStatus(), UserStatus.UPDATED);
        assertEquals(userResult.getUserDTO(), updateDTO);
        assertEquals(userResult.getMessage(), "User did not exist, a new user was created: " + updateDTO.getUserId());
    }

    @Test
    void test_whenSentUserToDelete_thenTheUserIsDeleted() throws Exception {
        UserDTO originalDTO = new UserDTOBuilder().build();
        UserModel saveModel = new UserModelBuilder().withAllFields(originalDTO.getUserId(), originalDTO.getFirstName(),
                originalDTO.getLastName(), originalDTO.getCreationDate()).build();
        userRepository.saveAndFlush(saveModel);

        MvcResult mvcResult = mockMvc.perform(delete("/user/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(originalDTO)))
                .andExpect(status().isOk())
                .andReturn();

        UserModel deletedUser = userRepository.findByUserId(originalDTO.getUserId());
        assertNull(deletedUser);

        UserResponse userResponse = om.readValue(mvcResult.getResponse().getContentAsString(), UserResponse.class);
        UserResult userResult = userResponse.getUserResult();
        assertEquals(userResult.getUserStatus(), UserStatus.DELETED);
        assertEquals(userResult.getUserDTO(), originalDTO);
        assertEquals(userResult.getMessage(), "User deleted successfully: " + originalDTO.getUserId());
    }

    @Test
    void test_whenUserToDeleteIsMissing_thenNoUserIsDeleted() throws Exception {
        UserDTO originalDTO = new UserDTOBuilder().build();

        MvcResult mvcResult = mockMvc.perform(delete("/user/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(originalDTO)))
                .andExpect(status().isOk())
                .andReturn();

        UserResponse userResponse = om.readValue(mvcResult.getResponse().getContentAsString(), UserResponse.class);
        UserResult userResult = userResponse.getUserResult();
        assertEquals(userResult.getUserStatus(), UserStatus.NONE);
        assertEquals(userResult.getUserDTO(), originalDTO);
        assertEquals(userResult.getMessage(), "User was not found for deletion: " + originalDTO.getUserId());
    }
}
