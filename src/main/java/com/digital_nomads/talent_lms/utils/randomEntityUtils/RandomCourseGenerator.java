package com.digital_nomads.talent_lms.utils.randomEntityUtils;
import com.github.javafaker.Faker;
import com.digital_nomads.talent_lms.entity.Course;
/**
 * @author Gera
 * рандомно генирирует поля на странице Add course(create)
 * **/
public class RandomCourseGenerator {

    Faker faker = new Faker();

    public String randomCourseName() {
        return faker.company().name();
    }

    public String randomDescription() {
        return faker.company().catchPhrase();
    }

    public String randomCourseCode() {
        return faker.code().toString();
    }

    public String randomCoursePrice() {
        return faker.commerce().price();
    }

    public String randomCourseUrl() {
        return faker.internet().url();
    }

    public String randomCourseCapacity() {
        Integer randomCapacity = faker.number().numberBetween(4, 20);
        return randomCapacity.toString();
    }

    public Course randomCourse() {
        return new Course(randomCourseName(), randomDescription(),
                randomCourseCode(), randomCoursePrice(), randomCourseUrl(), randomCourseCapacity());
    }


}
