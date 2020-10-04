package com.sda.RentalCar.controller;

import com.sda.RentalCar.config.RestResponse;
import com.sda.RentalCar.domain.Car;
import com.sda.RentalCar.dto.CreateCarDto;
import com.sda.RentalCar.service.CarService;
import com.sda.RentalCar.view.CarView;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Data
@RestController
@RequestMapping(value = "/car")
public class CarController {


    private final CarService service;
    private final RestTemplate restTemplate;

    @Autowired
    public CarController(CarService service, RestTemplate restTemplate){
        this.service = service;
        this.restTemplate = restTemplate;
    }


    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CreateCarDto dto){

        Car c = service.save(dto);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAll() {
        List<Car> cars = service.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping
    public RestResponse getResponse(){
        RestResponse response = restTemplate.getForObject(USERS_URL)
    }

    @GetMapping("/external/{userId}")
    public List<CarView> getCars (@PathVariable Long id){
        return service
    }
}
