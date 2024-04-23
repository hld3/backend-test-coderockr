package com.dodson.backendcoderockr.domain.dto.user.status;

import com.dodson.backendcoderockr.domain.dto.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains the user result data for the response.
 */
public class UserResult {

        /**
         * The user status enum value.
         */
        @JsonProperty
        private UserStatus userStatus = UserStatus.NONE;

        /**
         * The user data.
         */
        @JsonProperty
        private UserDTO userDTO;

        /**
         * Gets the user status enum value.
         * @return the user status enum value.
         */
        public UserStatus getUserStatus() {
                return userStatus;
        }

        /**
         * Sets the user status.
         * @param theUserStatus the user status to set to.
         */
        public void setUserStatus(final UserStatus theUserStatus) {
                this.userStatus = theUserStatus;
        }

        /**
         * Gets the user DTO.
         * @return the user DTO.
         */
        public UserDTO getUserDTO() {
                return userDTO;
        }

        /**
         * Sets the user DTO.
         * @param theUserDTO the user DTO to set to.
         */
        public void setUserDTO(final UserDTO theUserDTO) {
                this.userDTO = theUserDTO;
        }
}
