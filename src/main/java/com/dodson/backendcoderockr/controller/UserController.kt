package com.dodson.backendcoderockr.controller

import com.dodson.backendcoderockr.domain.dto.user.UserDTO
import com.dodson.backendcoderockr.domain.response.UserResponse
import com.dodson.backendcoderockr.service.CreateUserService
import com.dodson.backendcoderockr.service.DeleteUserService
import com.dodson.backendcoderockr.service.UpdateUserService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val createUserService: CreateUserService,
    private val updateUserService: UpdateUserService,
    private val deleteUserService: DeleteUserService,
) {
    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping("/create")
    fun createNewUser(
        @Valid @RequestBody userDTO: UserDTO,
    ): ResponseEntity<UserResponse> =
        UserResponse().apply {
            userResult = createUserService.createNewUser(userDTO)
            logger.info("New user created: ${userDTO.userId}")
        }.let { ResponseEntity.ok(it) }

    @PostMapping("/update")
    fun updateUser(
        @Valid @RequestBody userDTO: UserDTO,
    ): ResponseEntity<UserResponse> =
        UserResponse().apply {
            userResult = updateUserService.updateUser(userDTO)
            logger.info("User updated: ${userDTO.userId}")
        }.let { ResponseEntity.ok(it) }

    @DeleteMapping("/delete")
    fun deleteUser(
        @Valid @RequestBody userDTO: UserDTO,
    ): ResponseEntity<UserResponse> =
        UserResponse().apply {
            userResult = deleteUserService.deleteUser(userDTO)
            logger.info("User deleted: ${userDTO.userId}")
        }.let { ResponseEntity.ok(it) }
}
