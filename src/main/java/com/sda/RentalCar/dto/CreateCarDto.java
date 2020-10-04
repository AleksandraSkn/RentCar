package com.sda.RentalCar.dto;

import lombok.Data;

@Data
public class CreateCarDto {

    private String model;
    private Long year;
    private String mark;

}
