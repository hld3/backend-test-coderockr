package com.dodson.backendcoderockr.domain.model;

import java.util.UUID;

import com.github.javafaker.Faker;

public class UserModelBuilder {

    private Faker faker = new Faker();

    private UUID userId = UUID.randomUUID();
    private String firstName = faker.name().firstName();
    private String lastName = faker.name().lastName();
    private long creationDate = faker.number().randomNumber(13, true);

    public UserModel build() {
        UserModel user = new UserModel();
        user.setUserId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCreationDate(creationDate);
        return user;
    }

    public UserModelBuilder withAllFields(final UUID theUserId, final String theFirstName, final String theLastName,
            final long theCreationDate) {
        this.userId = theUserId;
        this.firstName = theFirstName;
        this.lastName = theLastName;
        this.creationDate = theCreationDate;
        return this;
    }

    public UserModelBuilder withUserId(final UUID theUserId) {
        this.userId = theUserId;
        return this;
    }
}
