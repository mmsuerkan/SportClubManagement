package com.example.backend.service;

import com.example.backend.model.Coordinator;
import com.example.backend.model.Parent;
import com.example.backend.repository.CoordinatorRepository;
import com.example.backend.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {
    @Autowired
    private ParentRepository parentRepository;

    public List<Parent> listAllParent(){
        return parentRepository.findAll();
    }

    public Parent getParentById(Long id){
        return parentRepository.findById(id).get();
    }

    public void deleteParentById(Long id){
        parentRepository.deleteById(id);
    }

    public void addParent(Parent newClub){
        parentRepository.save(newClub);
    }

    public void updateParent(Long updatedParentId,Parent updatedParent){

        Parent oldParent = parentRepository.findById(updatedParent.getId()).get();
        updatedParent.setId(oldParent.getId());
        parentRepository.save(updatedParent);

    }
}
