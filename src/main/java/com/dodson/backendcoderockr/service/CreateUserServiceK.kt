package com.dodson.backendcoderockr.service

import com.dodson.backendcoderockr.domain.dto.user.UserDTO
import com.dodson.backendcoderockr.domain.dto.user.status.UserResult
import com.dodson.backendcoderockr.domain.dto.user.status.UserStatus
import com.dodson.backendcoderockr.domain.model.UserModel
import com.dodson.backendcoderockr.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class CreateUserServiceK(private val userRepository: UserRepository) {

    fun createNewUser(userDTO: UserDTO): UserResult {
        val userModel = UserModel()
        userModel.userId = userDTO.userId
        userModel.firstName = userDTO.firstName
        userModel.lastName = userDTO.lastName
        userModel.creationDate = userDTO.creationDate
        userRepository.save(userModel)

        val userResult = UserResult()
        userResult.userDTO = userDTO
        userResult.userStatus = UserStatus.CREATED
        userResult.message = "User was created: ${userDTO.userId}"
        return userResult
    }
}
