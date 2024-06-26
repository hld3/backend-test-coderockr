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
     * @param theUserId       the user id.
     * @param theFirstName    the first name.
     * @param theLastName     the last name.
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

    @Override
    public final String toString() {
        return "UserDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", creationDate=" + creationDate + "]";
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + (int) (creationDate ^ (creationDate >>> 32));
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDTO other = (UserDTO) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (creationDate != other.creationDate)
            return false;
        return true;
    }
}
