package com.example.backend.controllers;

import com.example.backend.common.ApiResponse;
import com.example.backend.model.Branch;
import com.example.backend.model.Club;
import com.example.backend.service.BranchService;
import com.example.backend.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/")
    public ResponseEntity<List<Branch>> getBranches() {

        List<Branch> branches = branchService.listAllBranch();
        return new ResponseEntity<List<Branch>>(branches, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addBranch(@RequestBody Branch branch) {

        branchService.addBranch(branch);
        return new ResponseEntity<>(new ApiResponse(true, "Branch has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{branchId}")
    public ResponseEntity<ApiResponse> updateBranch(@PathVariable("branchId") Long clubID, @RequestBody Branch branch) {

        branchService.updateBranch(clubID, branch);
        return new ResponseEntity<>(new ApiResponse(true, "Branch has been updated"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{branchId}")
    public ResponseEntity<ApiResponse> deleteClub(@PathVariable("branchId") Long branchId)  {

        try {
            branchService.deleteBranchById(branchId);
            return new ResponseEntity<>(new ApiResponse(true, "Branch has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Branch not found"), HttpStatus.NO_CONTENT);
        }
    }

}
