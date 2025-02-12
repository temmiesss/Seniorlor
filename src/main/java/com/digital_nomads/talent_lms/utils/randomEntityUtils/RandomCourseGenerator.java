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

    public String randomCourseName100Char(){
        return faker.lorem().characters(101,150);
    }

    public String courseNameEmpty(){
        return "";
    }

    public String randomDescription() {
        return faker.company().catchPhrase();
    }

    public String randomCourseCode() {
        return String.valueOf(faker.number().numberBetween(1,10));
    }

    public String randomCourseCodeExceed(){
        return faker.lorem().characters(22, 34);
    }

    public String randomCoursePrice() {
        return String.valueOf(faker.number().numberBetween(50,100));
    }

    public String randomCoursePriceInvalid(){
        return faker.lorem().sentence();
    }

    public String randomCourseUrl() {
        return faker.internet().url();
    }

    public String randomCourseUrlInvalid() {
        return faker.lorem().sentence();
    }

    public String randomCourseCapacity() {
       return String.valueOf(faker.number().numberBetween(4,20));
    }

    public String randomCourseCapacityInvalid() {
        return faker.lorem().sentence();
    }

    public String randomCourseCategory(){
        return String.valueOf(faker.number().numberBetween(1,59));
    }

    public String randomCourseTimeLimit(){
        return String.valueOf(faker.number().numberBetween(1,59));
    }

    public Course randomCourse() {
        return new Course(randomCourseName(), randomDescription(),
                randomCourseCode(), randomCoursePrice(), randomCourseUrl(), randomCourseCapacity(),randomCourseCategory(),randomCourseTimeLimit());
    }


}
