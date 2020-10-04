package com.sda.RentalCar.view;

import lombok.Data;

import javax.persistence.Id;

@Data
public class CarView {

    public Long id;
    public String model;
    public Long year;
    public String mark;
}
