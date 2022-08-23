package com.example.backend.controllers;

import com.example.backend.common.ApiResponse;
import com.example.backend.dto.ClubDto;
import com.example.backend.dto.UserDto;
import com.example.backend.model.Club;
import com.example.backend.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/")
    public ResponseEntity<List<ClubDto>> getUsers() {

        List<ClubDto> clubs = clubService.getClubs();


        return new ResponseEntity<List<ClubDto>>(clubs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ClubDto clubDto) {

        clubService.addClub(clubDto);
        return new ResponseEntity<>(new ApiResponse(true, "Club has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{clubID}")
    public ResponseEntity<ApiResponse> updateClub(@PathVariable("clubID") Long clubID, @RequestBody ClubDto clubDto) {


            clubService.updateClub(clubID, clubDto);
            return new ResponseEntity<>(new ApiResponse(true, "Club has been updated"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{clubID}")
    public ResponseEntity<ApiResponse> deleteClub(@PathVariable("clubID") Long clubID)  {


        try {
            clubService.deleteClub(clubID);
            return new ResponseEntity<>(new ApiResponse(true, "Club has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Club not found"), HttpStatus.NO_CONTENT);
        }
    }
}
