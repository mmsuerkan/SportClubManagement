package com.example.backend.controllers;

import com.example.backend.common.ApiResponse;
import com.example.backend.dto.UserDto;
import com.example.backend.model.Club;
import com.example.backend.model.User;
import com.example.backend.service.ClubService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClubService clubService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUsers() {

        List<UserDto> userDtos = userService.getUsers();

        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody UserDto userDto) {
        Optional<Club> optionalClub = clubService.readClub(userDto.getClub_id());
        if (!optionalClub.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Club is invalid"), HttpStatus.CONFLICT);
        }
        Club club = optionalClub.get();
        userService.addUser(userDto, club);
        return new ResponseEntity<>(new ApiResponse(true, "User has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{userID}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable("userID") Long userID, @RequestBody UserDto userDto) {

        if (clubService.readClub(userDto.getClub_id()).isPresent()) {
            userService.updateUser(userID, userDto, userDto.getClub_id());
            return new ResponseEntity<>(new ApiResponse(true, "User has been updated"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ApiResponse(false, "Club is invalid"), HttpStatus.CONFLICT);

    }

    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userID") Long userID)  {


        try {
            userService.deleteUser(userID);
            return new ResponseEntity<>(new ApiResponse(true, "User has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "User not found"), HttpStatus.NO_CONTENT);
        }
    }


}
