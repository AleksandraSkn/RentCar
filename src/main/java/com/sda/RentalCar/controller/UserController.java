package com.sda.RentalCar.controller;

import com.sda.RentalCar.domain.Car;
import com.sda.RentalCar.domain.User;
import com.sda.RentalCar.dto.CreateUserDto;
import com.sda.RentalCar.service.UserService;
import com.sda.RentalCar.view.CarView;
import com.sda.RentalCar.view.UserAndCar;
import com.sda.RentalCar.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final RestTemplate restTemplate;
    private final UserService service;
    private static final String CAR_SVC_URL = "http://localhost:8081/car/external";

    @Autowired
    public UserController(UserService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto dto) {
        User u = service.save(dto);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = service.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/external/{userId}")
    public UserView findUserById(@PathVariable Long userId) {
        User user = service.findUserById(userId);
        return  new UserView(user.getId(), user.getName(), user.getSurname(), user.getAge());

    }

    @GetMapping("/external/allcarsforuser/{userId}")
    public List<UserAndCar> matchUserWithCars(@PathVariable Long userId) {
        User user = service.findUserById(userId);
        UserView uv = new UserView(user.getId(), user.getName(), user.getSurname(), user.getAge());
        CarView[] cars = getAllCars();
        ArrayList<UserAndCar> uac = new ArrayList<>();
        for(CarView cv : cars){
            uac.add(new UserAndCar(uv, cv));
        }
        return uac;
    }
    private CarView[] getAllCars(){
        return restTemplate.getForObject(CAR_SVC_URL, CarView[].class);
    }






}
