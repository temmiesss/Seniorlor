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
        return String.valueOf(faker.number().numberBetween(1,10));
    }

    public String randomCoursePrice() {
        return String.valueOf(faker.number().numberBetween(50,100));
    }

    public String randomCourseUrl() {
        return faker.internet().url();
    }

    public String randomCourseCapacity() {
       return String.valueOf(faker.number().numberBetween(4,20));
    }

    public Course randomCourse() {
        return new Course(randomCourseName(), randomDescription(),
                randomCourseCode(), randomCoursePrice(), randomCourseUrl(), randomCourseCapacity());
    }


}
