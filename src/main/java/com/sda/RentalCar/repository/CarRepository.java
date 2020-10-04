package com.sda.RentalCar.repository;

import com.sda.RentalCar.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository <Car, Long> {

    List<Car> findAllByUserId (Long id);
}
