package com.dodson.backendcoderockr.service

import com.dodson.backendcoderockr.domain.dto.user.UserDTO
import com.dodson.backendcoderockr.domain.dto.user.status.UserResult
import com.dodson.backendcoderockr.domain.dto.user.status.UserStatus
import com.dodson.backendcoderockr.domain.model.UserModel
import com.dodson.backendcoderockr.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UpdateUserService(private val userRepository: UserRepository) {
    private val logger = LoggerFactory.getLogger(UpdateUserService::class.java)

    fun updateUser(user: UserDTO): UserResult {
        val toUpdate = userRepository.findByUserId(user.userId)
        return UserResult().apply {
            userDTO = user
            userStatus = UserStatus.UPDATED
            message =
                if (toUpdate != null) {
                    updateExistingUser(toUpdate, userDTO)
                    "User was updated successfully: ${user.userId}"
                } else {
                    createNewUser(user)
                    "User did not exist, a new user was created: ${user.userId}"
                }
        }
    }

    private fun updateExistingUser(
        userModel: UserModel,
        userDTO: UserDTO,
    ) {
        userModel.apply {
            firstName = userDTO.firstName
            lastName = userDTO.lastName
        }
        userRepository.save(userModel)
        logger.info("User, ${userDTO.userId}, was updated successfully.")
    }

    private fun createNewUser(userDTO: UserDTO) {
        val userModel =
            UserModel().apply {
                userId = userDTO.userId
                firstName = userDTO.firstName
                lastName = userDTO.lastName
                creationDate = userDTO.creationDate
            }
        userRepository.save(userModel)
        logger.info("Could not update, the user: ${userDTO.userId} does not exist. Creating a new user.")
    }
}
