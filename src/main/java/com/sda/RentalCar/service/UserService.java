package com.sda.RentalCar.service;

import com.sda.RentalCar.domain.Car;
import com.sda.RentalCar.domain.User;
import com.sda.RentalCar.dto.CreateUserDto;
import com.sda.RentalCar.repository.UserRepository;
import com.sda.RentalCar.view.CarView;
import com.sda.RentalCar.view.UserAndCar;
import com.sda.RentalCar.view.UserView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(CreateUserDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .age(dto.getAge())
                .build();

        repository.save(user);
        return user;
    }
    public List<User> findAll() {
        return repository.findAll();
    }

    public User findUserById(Long userId) {
        return repository.findById(userId).get();

    }

}


