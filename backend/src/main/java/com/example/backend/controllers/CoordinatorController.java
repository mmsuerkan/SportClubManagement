package com.example.backend.controllers;

import com.example.backend.common.ApiResponse;
import com.example.backend.model.Club;
import com.example.backend.model.Coordinator;
import com.example.backend.service.ClubService;
import com.example.backend.service.CoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coordinator")
public class CoordinatorController {

    @Autowired
    private CoordinatorService coordinatorService;

    @GetMapping("/")
    public ResponseEntity<List<Coordinator>> getCoordinator() {

        List<Coordinator> clubs = coordinatorService.listAllCoordinator();
        return new ResponseEntity<List<Coordinator>>(clubs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCoordinator(@RequestBody Coordinator coordinator) {

        coordinatorService.addCoordinator(coordinator);
        return new ResponseEntity<>(new ApiResponse(true, "Coordinator has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{CoordinatorID}")
    public ResponseEntity<ApiResponse> updateCoordinator(@PathVariable("CoordinatorID") Long CoordinatorIDID, @RequestBody Coordinator coordinator) {

        coordinatorService.updateCoordinator(CoordinatorIDID, coordinator);
        return new ResponseEntity<>(new ApiResponse(true, "Coordinator has been updated"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{CoordinatorID}")
    public ResponseEntity<ApiResponse> deleteCoordinatorById(@PathVariable("CoordinatorID") Long CoordinatorID)  {

        try {
            coordinatorService.deleteCoordinatorById(CoordinatorID);
            return new ResponseEntity<>(new ApiResponse(true, "Coordinator has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Coordinator not found"), HttpStatus.NO_CONTENT);
        }
    }

}
