package com.example.backend.service;

import com.example.backend.dto.ClubDto;
import com.example.backend.model.Club;
import com.example.backend.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public Optional<Club> readClub(Long club_id) {
        return clubRepository.findById(club_id);
    }

    public void addClub(ClubDto clubDto) {
        clubRepository.save(new Club(clubDto.getId(), clubDto.getName(), clubDto.getBranchName()));
    }
@Transactional
    public void updateClub(Long clubID, ClubDto clubDto) {
        Club oldClub = clubRepository.findById(clubID).get();

        oldClub.setName(clubDto.getName());
        oldClub.setBranchName(clubDto.getBranchName());

        clubRepository.save(oldClub);
    }
    @Transactional
    public void deleteClub(Long clubID) {
        clubRepository.deleteById(clubID);
    }

    public List<ClubDto> getClubs() {
        List<ClubDto> clubDtoList = new ArrayList<>();

        for (Club club : clubRepository.findAll()) {
            clubDtoList.add(new ClubDto(club.getId(), club.getName(), club.getBranchName()));
        }
        return clubDtoList;
    }
}
