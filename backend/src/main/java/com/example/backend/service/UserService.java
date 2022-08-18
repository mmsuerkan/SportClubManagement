package com.example.backend.service;

import com.example.backend.dto.UserDto;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getUsers() {
        List<UserDto> userDtos= new ArrayList<>();


        for ( User user :userRepository.findAll()) {
            userDtos.add(new UserDto(user.getFirstName(),user.getLastName()));
        }
        return userDtos;
    }

}
