package com.digital_nomads.talent_lms.utils.randomEntityUtils;

import com.github.javafaker.Faker;
import com.digital_nomads.talent_lms.entity.User;

public class RandomUserGenerator {

    Faker faker = new Faker();

    public String randomFirstName(){
        return faker.name().firstName();
    }

    public String randomLastName(){
        return faker.name().lastName();
    }

    public String randomEmail(){
        return faker.internet().emailAddress();
    }

    public String randomUsername(){
        return faker.name().username();
    }

    public String randomPassword(){
        return faker.internet().password();
    }

    public String randomBio() {
        return faker.lorem().sentence(); // Генерация случайного био
    }

    public User randomUser(){
        return new User(randomFirstName(), randomLastName(), randomUsername(), randomEmail(), randomPassword(), randomBio());
    }
}
