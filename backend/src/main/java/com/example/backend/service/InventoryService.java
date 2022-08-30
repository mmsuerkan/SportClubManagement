package com.example.backend.service;

import com.example.backend.model.Inventory;
import com.example.backend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> listAllInventory(){
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id){
        return inventoryRepository.findById(id).get();
    }

    public void deleteInventoryById(Long id){
        inventoryRepository.deleteById(id);
    }

    public void addInventory(Inventory newInventory){
        inventoryRepository.save(newInventory);
    }

    public void updateInventory(Long updatedInventoryId,Inventory updatedInventory){

        Inventory oldInventory = inventoryRepository.findById(updatedInventory.getId()).get();
        updatedInventory.setId(oldInventory.getId());
        inventoryRepository.save(updatedInventory);

    }
}
