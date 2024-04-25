package com.dodson.backendcoderockr.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dodson.backendcoderockr.domain.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

        /**
         * Testing @Query.
         * Retrieve the user with the given UUID.
         * @param userId The userId to search for.
         * @return The {@link UserModel} or null if not found.
         */
        @Query("SELECT u FROM UserModel u WHERE userId = :userId")
        UserModel findUserByUserId(@Param("userId") UUID userId);

        /**
         * Retrieve the user with the given UUID.
         * @param userId The userId to search for.
         * @return The {@link UserModel} or null if not found.
         */
        UserModel findByUserId(UUID userId);
}
