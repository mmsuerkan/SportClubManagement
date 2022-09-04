package com.example.backend.controllers;

import com.example.backend.common.ApiResponse;
import com.example.backend.dto.TrainerDto;
import com.example.backend.model.Trainer;
import com.example.backend.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/")
    public ResponseEntity<List<Trainer>> listAllTrainers() {

        List<Trainer> trainers = trainerService.listAllTrainer();
            return new ResponseEntity<List<Trainer>>(trainers, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTrainer(@RequestBody TrainerDto trainerDto) {

        trainerService.addTrainer(trainerDto);
        return new ResponseEntity<>(new ApiResponse(true, "Trainer has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{trainerID}")
    public ResponseEntity<ApiResponse> updateTrainer(@PathVariable("trainerID") Long trainerID, @RequestBody TrainerDto trainerDto) {

        trainerService.updateTrainer(trainerID, trainerDto);
        return new ResponseEntity<>(new ApiResponse(true, "Trainer has been updated"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{trainerID}")
    public ResponseEntity<ApiResponse> deleteTrainerById(@PathVariable("trainerID") Long trainerID)  {

        try {
            trainerService.deleteTrainerById(trainerID);
            return new ResponseEntity<>(new ApiResponse(true, "Trainer has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Trainer not found"), HttpStatus.NO_CONTENT);
        }
    }
}
