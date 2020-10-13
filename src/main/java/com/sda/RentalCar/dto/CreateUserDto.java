package com.sda.RentalCar.dto;

import lombok.Data;

@Data
public class CreateUserDto {

    private String name;

    private String surname;

    private Long age;
}
