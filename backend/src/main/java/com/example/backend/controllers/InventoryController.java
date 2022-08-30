package com.example.backend.controllers;

import com.example.backend.common.ApiResponse;
import com.example.backend.model.Coordinator;
import com.example.backend.model.Inventory;
import com.example.backend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/")
    public ResponseEntity<List<Inventory>> getCoordinator() {

        List<Inventory> inventories = inventoryService.listAllInventory();
        return new ResponseEntity<List<Inventory>>(inventories, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addInventory(@RequestBody Inventory inventory) {

        inventoryService.addInventory(inventory);
        return new ResponseEntity<>(new ApiResponse(true, "Inventory has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{inventoryId}")
    public ResponseEntity<ApiResponse> updateInventory(@PathVariable("inventoryId") Long inventoryId, @RequestBody Inventory inventory) {

        inventoryService.updateInventory(inventoryId, inventory);
        return new ResponseEntity<>(new ApiResponse(true, "Inventory has been updated"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{inventoryId}")
    public ResponseEntity<ApiResponse> deleteInventoryById(@PathVariable("inventoryId") Long inventoryId)  {

        try {
            inventoryService.deleteInventoryById(inventoryId);
            return new ResponseEntity<>(new ApiResponse(true, "Inventory has been deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Inventory not found"), HttpStatus.NO_CONTENT);
        }
    }
}
