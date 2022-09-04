package com.example.backend.service;

import com.example.backend.dto.ParentDto;
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

    public void addParent(ParentDto parentDto){

        Parent parent = new Parent();
        parent.setId(parentDto.getId());
        parent.setName(parentDto.getName());
        parent.setSurname(parentDto.getSurname());

        parentRepository.save(parent);
    }

    public void updateParent(Long updatedParentId,ParentDto parentDto){

        Parent oldParent = parentRepository.findById(updatedParentId).get();

        oldParent.setName(parentDto.getName());
        oldParent.setSurname(parentDto.getSurname());

        parentRepository.save(oldParent);

    }
}
