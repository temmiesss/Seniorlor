package com.digital_nomads.talent_lms.entity;

import lombok.*;
/**
 * @author Gera
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Course {

    private String courseName;
    private String description;
    private String courseCode;
    private String  price;
    private String video;
    private String  capacity;
    private String category;
    private String timeLimit;

}
