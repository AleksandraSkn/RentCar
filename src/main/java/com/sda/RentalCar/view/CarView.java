package com.sda.RentalCar.view;

import lombok.Data;

import javax.persistence.Id;

@Data
public class CarView {

    public CarView(Long id, Long userId, String model, Long year, String mark) {
        this.id = id;
        this.userId = userId;
        this.model = model;
        this.year = year;
        this.mark = mark;
    }

    public CarView() {
    }

    private Long id;

    private Long userId;
    private String model;
    private Long year;
    private String mark;
}
