package com.example.backend.controllers;

import com.example.backend.common.ApiResponse;
import com.example.backend.dto.ParentDto;
import com.example.backend.model.Coordinator;
import com.example.backend.model.Parent;
import com.example.backend.service.CoordinatorService;
import com.example.backend.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    private ParentService parentService;

    @GetMapping("/")
    public ResponseEntity<List<Parent>> listAllParent() {

        List<Parent> parents = parentService.listAllParent();
        return new ResponseEntity<List<Parent>>(parents, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addParent(@RequestBody ParentDto parentDto) {

        parentService.addParent(parentDto);
        return new ResponseEntity<>(new ApiResponse(true, "Parent has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{parentID}")
    public ResponseEntity<ApiResponse> updateParent(@PathVariable("parentID") Long parentID, @RequestBody ParentDto parentDto) {

        parentService.updateParent(parentID, parentDto);
        return new ResponseEntity<>(new ApiResponse(true, "Parent has been updated"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{parentID}")
    public ResponseEntity<ApiResponse> deleteParentById(@PathVariable("parentID") Long parentID)  {

        try {
            parentService.deleteParentById(parentID);
            return new ResponseEntity<>(new ApiResponse(true, "Parent has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Parent not found"), HttpStatus.NO_CONTENT);
        }
    }
}
