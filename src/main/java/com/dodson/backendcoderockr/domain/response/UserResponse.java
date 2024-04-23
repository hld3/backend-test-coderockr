package com.dodson.backendcoderockr.domain.response;

import java.util.Map;

import com.dodson.backendcoderockr.domain.dto.user.status.UserResult;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The response returned when a user endpointed is accessed.
 */
public class UserResponse {

        /**
         * The message to return.
         */
        @JsonProperty
        private String message;

        /**
         * The user result after processing.
         */
        @JsonProperty
        private UserResult userResult;

        /**
         * The errors to return if the request is invalid.
         * TODO make an Error object to return.
         */
        @JsonProperty
        private Map<String, String> errors;

        /**
         * Gets the message.
         * @return the message.
         */
        public String getMessage() {
                return message;
        }

        /**
         * Sets the message.
         * @param newMessage the message to set.
         */
        public void setMessage(final String newMessage) {
                this.message = newMessage;
        }

        /**
         * Gets the user result.
         * @return the user result.
         */
        public UserResult getUserResult() {
                return userResult;
        }

        /**
         * Sets the user result.
         * @param newUserResult the user result.
         */
        public void setUserResult(final UserResult newUserResult) {
                this.userResult = newUserResult;
        }

        /**
         * Gets the errors.
         * @return the errors.
         */
        public Map<String, String> getErrors() {
                return errors;
        }

        /**
         * Sets the errors.
         * @param theErrors the map of errors.
         */
        public void setErrors(final Map<String, String> theErrors) {
                this.errors = theErrors;
        }
}
