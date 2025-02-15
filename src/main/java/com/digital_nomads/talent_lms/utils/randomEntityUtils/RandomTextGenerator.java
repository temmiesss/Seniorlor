package com.digital_nomads.talent_lms.utils.randomEntityUtils;

import com.github.javafaker.Faker;

public class RandomTextGenerator {

    Faker faker = new Faker();

    public String randomText(){
       return faker.lorem().word();
    }

    public String randomText1(){
        return faker.lorem().sentence();
    }
}
