package com.sda.RentalCar.view;

import lombok.Data;

@Data
public class UserView {

    public UserView() {
    }

    public UserView(Long id, String name, String surname, Long age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Long id;
    public String name;
    public String surname;
    public Long age;
}
