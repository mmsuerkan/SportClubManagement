package com.example.backend.controllers;

import com.example.backend.dto.UserDto;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUsers() {


        // get items in the cart for the user.
        List<UserDto> userDtos = userService.getUsers();

        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }

}
