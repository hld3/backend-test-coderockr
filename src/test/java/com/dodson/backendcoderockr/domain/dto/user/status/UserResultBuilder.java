package com.dodson.backendcoderockr.domain.dto.user.status;

import com.dodson.backendcoderockr.domain.dto.user.UserDTOBuilder;

public class UserResultBuilder {

    public UserResult build() {
        UserResult result = new UserResult();
        result.setUserDTO(new UserDTOBuilder().build());
        result.setUserStatus(UserStatusBuilder.randomStatus());
        return result;
    }
}
