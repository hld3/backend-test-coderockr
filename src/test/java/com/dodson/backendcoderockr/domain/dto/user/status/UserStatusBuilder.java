package com.dodson.backendcoderockr.domain.dto.user.status;

import java.util.Random;

public class UserStatusBuilder {

	public static UserStatus randomStatus() {
		Random random = new Random();
		UserStatus[] statuses = UserStatus.values();
		int x = random.nextInt(statuses.length);
		return statuses[x];
	}
}
