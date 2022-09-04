package com.example.backend.service;

import com.example.backend.dto.InventoryDto;
import com.example.backend.model.Inventory;
import com.example.backend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryDto> listAllInventory(){
        List<Inventory> inventoryList = inventoryRepository.findAll();

        ArrayList<InventoryDto> inventoryDtos = new ArrayList<>();

        for (Inventory inventory : inventoryList) {
            InventoryDto inventoryDto = new InventoryDto();
            inventoryDto.setId(inventory.getId());
            inventoryDto.setName(inventory.getName());
            inventoryDtos.add(inventoryDto);
        }

        return inventoryDtos;
    }

    public Inventory getInventoryById(Long id){
        return inventoryRepository.findById(id).get();
    }

    public void deleteInventoryById(Long id){
        List<Inventory> all = inventoryRepository.findAll();
        inventoryRepository.deleteById(id);
    }

    public void addInventory(InventoryDto inventoryDto){
        Inventory inventory = new Inventory();
        inventory.setId(inventoryDto.getId());
        inventory.setName(inventoryDto.getName());
        inventoryRepository.save(inventory);
    }

    public void updateInventory(Long updatedInventoryId,Inventory updatedInventory){

        Inventory oldInventory = inventoryRepository.findById(updatedInventory.getId()).get();
        updatedInventory.setId(oldInventory.getId());
        inventoryRepository.save(updatedInventory);

    }
}
