package utils.randomEntityUtils;

import com.github.javafaker.Faker;
import entity.User;

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

    public User randomUser(){
        return new User(randomFirstName(), randomLastName(), randomUsername(), randomEmail(), randomPassword());
    }
}
