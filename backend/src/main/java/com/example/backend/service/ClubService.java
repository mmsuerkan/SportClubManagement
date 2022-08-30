package com.example.backend.service;

import com.example.backend.model.Branch;
import com.example.backend.model.Club;
import com.example.backend.repository.BranchRepository;
import com.example.backend.repository.ClubRepository;
import com.example.backend.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;

    public List<Club> listAllClub(){
        return clubRepository.findAll();
    }

    public Club getClubById(Long id){
        return clubRepository.findById(id).get();
    }

    public void deleteClubById(Long id){
        clubRepository.deleteById(id);
    }

    public void addClub(Club newClub){
        clubRepository.save(newClub);
    }

    public void updateClub(Long updatedClubId,Club updatedClub){

        Club oldClub = clubRepository.findById(updatedClubId).get();
        updatedClub.setId(oldClub.getId());
        clubRepository.save(updatedClub);
    }
}
