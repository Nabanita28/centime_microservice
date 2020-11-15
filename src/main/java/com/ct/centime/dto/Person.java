package com.ct.centime.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Person {

    @NotEmpty(message = "Please enter valid name")
    public String name;

    @NotEmpty(message = "Please enter valid surname")
    public String surname;
}
