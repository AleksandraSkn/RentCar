package com.sda.RentalCar.view;

import com.sda.RentalCar.domain.User;
import lombok.Data;

@Data
public class UserAndCar {

 private CarView carView;
 private UserView userView;

 public UserAndCar() {
 }

 public UserAndCar(UserView user, CarView cv) {
  carView = cv;
  userView = user;
 }
}
