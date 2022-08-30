package com.example.backend.controllers;

import com.example.backend.common.ApiResponse;
import com.example.backend.model.Club;
import com.example.backend.service.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/")
    public ResponseEntity<List<Club>> getClubs() {

        List<Club> clubs = clubService.listAllClub();
        return new ResponseEntity<List<Club>>(clubs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addClub(@RequestBody Club clubDto) {

        clubService.addClub(clubDto);
        return new ResponseEntity<>(new ApiResponse(true, "Club has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{clubID}")
    public ResponseEntity<ApiResponse> updateClub(@PathVariable("clubID") Long clubID, @RequestBody Club clubDto) {

        clubService.updateClub(clubID, clubDto);
        return new ResponseEntity<>(new ApiResponse(true, "Club has been updated"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{clubID}")
    public ResponseEntity<ApiResponse> deleteClub(@PathVariable("clubID") Long clubID)  {

        try {
            clubService.deleteClubById(clubID);
            return new ResponseEntity<>(new ApiResponse(true, "Club has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Club not found"), HttpStatus.NO_CONTENT);
        }
    }

}
