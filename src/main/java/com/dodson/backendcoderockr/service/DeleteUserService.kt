package com.dodson.backendcoderockr.service

import com.dodson.backendcoderockr.domain.dto.user.UserDTO
import com.dodson.backendcoderockr.domain.dto.user.status.UserResult
import com.dodson.backendcoderockr.domain.dto.user.status.UserStatus
import com.dodson.backendcoderockr.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DeleteUserService(private val userRepository: UserRepository) {
    private val logger = LoggerFactory.getLogger(DeleteUserService::class.java)

    fun deleteUser(user: UserDTO): UserResult =
        userRepository.findByUserId(user.userId)?.let { toDelete ->
            userRepository.delete(toDelete)
            logger.info("User was deleted successfully: ${user.userId}")
            UserResult(user, UserStatus.DELETED, "User was deleted successfully.")
        } ?: run {
            logger.info("User was not found for deletion: ${user.userId}")
            UserResult(user, UserStatus.NONE, "User was not found for deletion.")
        }
}
