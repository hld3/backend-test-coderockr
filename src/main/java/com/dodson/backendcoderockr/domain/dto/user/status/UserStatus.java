package com.dodson.backendcoderockr.domain.dto.user.status;

/**
 * The accepted values for a user's status.
 */
public enum UserStatus {
        /**
         * The user was created.
         */
        CREATED,

        /**
         * The user was updated.
         */
        UPDATED,

        /**
         * The user was deleted.
         */
        DELETED,

        /**
         * Nothing was done with the user.
         */
        NONE
}
