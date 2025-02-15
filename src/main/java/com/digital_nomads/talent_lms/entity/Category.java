package com.digital_nomads.talent_lms.entity;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Category {

    String catName = "Qwerty ST Trew";
    String  price = "12898";

    public Category() {
        this.catName = catName;
    }
}

