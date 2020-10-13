package com.sda.RentalCar.service;

import com.sda.RentalCar.domain.Car;
import com.sda.RentalCar.domain.User;
import com.sda.RentalCar.dto.CreateCarDto;
import com.sda.RentalCar.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CarService {

    private final CarRepository repository;


    @Autowired
    public CarService(CarRepository repository){
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

    public Car findCarById(Long carId) {
        return repository.findById(carId).get();

    }

}
