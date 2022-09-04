package com.example.backend.service;

import com.example.backend.dto.TrainerDto;
import com.example.backend.model.Trainer;
import com.example.backend.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public void deleteTrainerById(Long id){
        trainerRepository.deleteById(id);
    }

    @Transactional
    public void addTrainer(TrainerDto trainerDto){

        Trainer trainer = new Trainer();
        trainer.setId(trainerDto.getId());
        trainer.setName(trainerDto.getName());
        trainer.setSurname(trainerDto.getSurname());

        trainerRepository.save(trainer);
    }

    @Transactional
    public void updateTrainer(Long updatedTrainerId, TrainerDto trainerDto){

        Trainer oldTrainer = trainerRepository.findById(updatedTrainerId).get();

        oldTrainer.setName(trainerDto.getName());
        oldTrainer.setSurname(trainerDto.getSurname());

        trainerRepository.save(oldTrainer);

    }
}
