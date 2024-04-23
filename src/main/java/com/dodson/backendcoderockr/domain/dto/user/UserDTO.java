package com.dodson.backendcoderockr.domain.dto.user;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

/**
 * The user fields expected in the request.
 */
public class UserDTO {

        /**
         * The user id.
         */
        @NotNull
        @JsonProperty
        private UUID userId;

        /**
         * The user first name.
         */
        @JsonProperty
        private String firstName;

        /**
         * the user last name.
         */
        @JsonProperty
        private String lastName;

        /**
         * The user creation date.
         */
        @JsonProperty
        private long creationDate;

        /**
         * Empty constructor.
         */
        public UserDTO() {
        }

        /**
         * Constructor with all fields.
         * @param theUserId the user id.
         * @param theFirstName the first name.
         * @param theLastName the last name.
         * @param theCreationDate the creation date.
         */
        public UserDTO(final UUID theUserId,
                        final String theFirstName,
                        final String theLastName,
                        final long theCreationDate) {
                this.userId = theUserId;
                this.firstName = theFirstName;
                this.lastName = theLastName;
                this.creationDate = theCreationDate;
        }

        /**
         * Gets the user id.
         * @return the user id.
         */
        public UUID getUserId() {
                return userId;
        }

        /**
         * Sets the user id.
         * @param theUserId the user id to set to.
         */
        public void setUserId(final UUID theUserId) {
                this.userId = theUserId;
        }

        /**
         * Gets the first name of the user.
         * @return the first name.
         */
        public String getFirstName() {
                return firstName;
        }

        /**
         * Sets the first name of the user.
         * @param theFirstName the first name to set to.
         */
        public void setFirstName(final String theFirstName) {
                this.firstName = theFirstName;
        }

        /**
         * Gets the last name of the user.
         * @return the last name.
         */
        public String getLastName() {
                return lastName;
        }

        /**
         * Sets the last name of the user.
         * @param theLastName the last name.
         */
        public void setLastName(final String theLastName) {
                this.lastName = theLastName;
        }

        /**
         * Gets the creation date of the user.
         * @return the creation date.
         */
        public long getCreationDate() {
                return creationDate;
        }

        /**
         * Sets the creation date of the user.
         * @param theCreationDate the creation date.
         */
        public void setCreationDate(final long theCreationDate) {
                this.creationDate = theCreationDate;
        }
}
