package com.sda.RentalCar.controller;

import com.sda.RentalCar.config.RestResponse;
import com.sda.RentalCar.domain.Car;
import com.sda.RentalCar.dto.CreateCarDto;
import com.sda.RentalCar.service.CarService;
import com.sda.RentalCar.view.CarView;
import com.sda.RentalCar.view.UserAndCar;
import com.sda.RentalCar.view.UserView;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@RestController
@RequestMapping(value = "/car")
public class CarController {

    private static final String USER_SVC_URL = "http://localhost:8081/user/external/";

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

    //udostępniamy listę wszystich samochodów
    @GetMapping("/external")
    public ResponseEntity<List<CarView>> findAllExternal() {
        List<Car> cars = service.findAll();
        ArrayList<CarView> carViewList = new ArrayList<>();
        for(Car car : cars){
            carViewList.add(new CarView(car.getId(), car.getUserId(), car.getModel(), car.getYear(), car.getMark()));
        }
        System.out.println("\n\n\nGET!!!!!!!\n\n\n");
        return ResponseEntity.ok(carViewList);
    }

    //pobieramy dane usera ktory ma takie samo id jak samochod
    @GetMapping("/external/{carId}/{userId}")
    public ResponseEntity<UserAndCar> getUserDetails (@PathVariable Long carId, @PathVariable Long userId){
        //tutaj wolamy serwis do pobrania usera po id
        Car car = service.findCarById(carId);
        UserAndCar uac = new UserAndCar();
        uac.setCarView(new CarView(car.getId(), car.getUserId(), car.getModel(), car.getYear(), car.getMark()));
        //pobieramy usera o tym samym ID
        uac.setUserView(getUserDetailsByID(userId));
        return ResponseEntity.ok(uac);

    }

    private UserView getUserDetailsByID(Long userID){
        UserView user = restTemplate.getForObject(USER_SVC_URL+userID, UserView.class);
        return user;
    }
}
