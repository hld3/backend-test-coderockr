package com.dodson.backendcoderockr.repository

import com.dodson.backendcoderockr.domain.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<UserModel, Long> {
    /**
     * Retrieve the user with the given UUID.
     * @param userId The userId to search for.
     * @return The [UserModel] or null if not found.
     */
    fun findByUserId(userId: UUID?): UserModel?
}
