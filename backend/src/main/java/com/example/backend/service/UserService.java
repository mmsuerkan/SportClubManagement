package com.example.backend.service;

import com.example.backend.model.Coordinator;
import com.example.backend.model.User;
import com.example.backend.repository.CoordinatorRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public void addUser(User newUser){
        userRepository.save(newUser);
    }

    public void updateUser(Long updatedUserId,User updatedUser){

        User oldUser = userRepository.findById(updatedUser.getId()).get();
        updatedUser.setId(oldUser.getId());
        userRepository.save(updatedUser);

    }
}
