package com.example.backend.service;

import com.example.backend.model.Coordinator;
import com.example.backend.model.Trainer;
import com.example.backend.repository.CoordinatorRepository;
import com.example.backend.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public List<Trainer> listAllTrainer(){
        return trainerRepository.findAll();
    }

    public Trainer getTrainerById(Long id){
        return trainerRepository.findById(id).get();
    }

    public void deleteTrainerById(Long id){
        trainerRepository.deleteById(id);
    }

    public void addTrainer(Trainer newTrainer){
        trainerRepository.save(newTrainer);
    }

    public void updateTrainer(Long updatedTrainerId,Trainer updatedTrainer){

        Trainer oldTrainer = trainerRepository.findById(updatedTrainer.getId()).get();
        updatedTrainer.setId(oldTrainer.getId());
        trainerRepository.save(updatedTrainer);

    }
}
