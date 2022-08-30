package com.example.backend.controllers;

import com.example.backend.common.ApiResponse;
import com.example.backend.model.Coordinator;
import com.example.backend.model.User;
import com.example.backend.service.CoordinatorService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> listAllUser() {

        List<User> users = userService.listAllUser();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCoordinator(@RequestBody User user) {

        userService.addUser(user);
        return new ResponseEntity<>(new ApiResponse(true, "User has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{UserID}")
    public ResponseEntity<ApiResponse> updateCoordinator(@PathVariable("UserID") Long userID, @RequestBody User user) {

        userService.updateUser(userID, user);
        return new ResponseEntity<>(new ApiResponse(true, "User has been updated"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<ApiResponse> deleteCoordinatorById(@PathVariable("userID") Long userID)  {

        try {
            userService.deleteUserById(userID);
            return new ResponseEntity<>(new ApiResponse(true, "User has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "User not found"), HttpStatus.NO_CONTENT);
        }
    }
}
