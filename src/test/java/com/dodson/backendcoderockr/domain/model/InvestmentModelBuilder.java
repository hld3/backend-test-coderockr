package com.dodson.backendcoderockr.domain.model;

import com.github.javafaker.Faker;

public class InvestmentModelBuilder {

    private Faker faker = new Faker();

    private double amount = faker.number().randomDouble(2, 1, 5);
    private long creationDate = faker.number().randomNumber(13, true);

    public InvestmentModel build() {
        InvestmentModel im = new InvestmentModel();
        im.setAmount(amount);
        im.setCreationDate(creationDate);
        return im;
    }
}
