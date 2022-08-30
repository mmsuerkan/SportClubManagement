package com.example.backend.service;

import com.example.backend.model.Club;
import com.example.backend.model.Coordinator;
import com.example.backend.repository.ClubRepository;
import com.example.backend.repository.CoordinatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatorService {

    @Autowired
    private CoordinatorRepository coordinatorRepository;

    public List<Coordinator> listAllCoordinator(){
        return coordinatorRepository.findAll();
    }

    public Coordinator getCoordinatorById(Long id){
        return coordinatorRepository.findById(id).get();
    }

    public void deleteCoordinatorById(Long id){
        coordinatorRepository.deleteById(id);
    }

    public void addCoordinator(Coordinator newClub){
        coordinatorRepository.save(newClub);
    }

    public void updateCoordinator(Long updatedCoordinatorId,Coordinator updatedCoordinator){

        Coordinator oldCoordinator = coordinatorRepository.findById(updatedCoordinator.getId()).get();
        updatedCoordinator.setId(oldCoordinator.getId());
        coordinatorRepository.save(updatedCoordinator);

    }
}
