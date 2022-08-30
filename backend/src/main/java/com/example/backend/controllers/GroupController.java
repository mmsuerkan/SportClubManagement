package com.example.backend.controllers;

import com.example.backend.common.ApiResponse;
import com.example.backend.model.Coordinator;
import com.example.backend.model.Group;
import com.example.backend.service.CoordinatorService;
import com.example.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public ResponseEntity<List<Group>> getGroup() {

        List<Group> groups = groupService.listAllGroup();
        return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addGroup(@RequestBody Group group) {

        groupService.addGroup(group);
        return new ResponseEntity<>(new ApiResponse(true, "Group has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{GroupId}")
    public ResponseEntity<ApiResponse> updateGroupId(@PathVariable("GroupId") Long GroupId, @RequestBody Group group) {

        groupService.updateGroup(GroupId, group);
        return new ResponseEntity<>(new ApiResponse(true, "Group has been updated"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{GroupId}")
    public ResponseEntity<ApiResponse> deleteGroupById(@PathVariable("GroupId") Long CoordinatorID)  {

        try {
            groupService.deleteGroupById(CoordinatorID);
            return new ResponseEntity<>(new ApiResponse(true, "Group has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Group not found"), HttpStatus.NO_CONTENT);
        }
    }

}
