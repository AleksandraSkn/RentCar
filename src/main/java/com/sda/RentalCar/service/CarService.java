package com.sda.RentalCar.service;

import com.sda.RentalCar.domain.Car;
import com.sda.RentalCar.dto.CreateCarDto;
import com.sda.RentalCar.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private static String USERS_URL = "http://localhost:8070/user/external/";

    private final CarRepository repository;

    @Autowired
    public CarService(CarRepository repository, CarRepository repository1){
        this.repository = repository;
    }

    public Car save(CreateCarDto dto){
        Car car = Car.builder()
                .mark(dto.getMark())
                .model(dto.getModel())
                .year(dto.getYear())
                .build();

        repository.save(car);
        return car;
    }

    public List<Car> findAll() {
        return repository.findAll();
    }

}
