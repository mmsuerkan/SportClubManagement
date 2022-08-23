package com.example.backend.service;

import com.example.backend.dto.UserDto;
import com.example.backend.model.Club;
import com.example.backend.model.User;
import com.example.backend.repository.ClubRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClubRepository clubRepository;

    public List<UserDto> getUsers() {

        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            userDtoList.add(new UserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getClub().getId()));
        }

        return userDtoList;
    }


    public void addUser(UserDto userDto, Club club) {

        User user = new User();

        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setClub(club);

        userRepository.save(user);
    }
    @Transactional
    public void updateUser(Long userID, UserDto userDto, Long club_id) {

        User user = userRepository.findById(userID).get();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setClub(clubRepository.findById(club_id).get());

        userRepository.save(user);

    }
    @Transactional
    public void deleteUser(Long userID) throws Exception {

        if(userRepository.findById(userID).isPresent()){
            userRepository.deleteById(userID);
        }else {
            throw new Exception("User not found");
        }
    }
}
