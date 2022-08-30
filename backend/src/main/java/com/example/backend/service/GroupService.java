package com.example.backend.service;

import com.example.backend.model.Coordinator;
import com.example.backend.model.Group;
import com.example.backend.repository.CoordinatorRepository;
import com.example.backend.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> listAllGroup(){
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id){
        return groupRepository.findById(id).get();
    }

    public void deleteGroupById(Long id){
        groupRepository.deleteById(id);
    }

    public void addGroup(Group newGroup){
        groupRepository.save(newGroup);
    }

    public void updateGroup(Long updatedGroupId,Group updatedGroup){

        Group oldGroup = groupRepository.findById(updatedGroup.getId()).get();
        updatedGroup.setId(oldGroup.getId());
        groupRepository.save(updatedGroup);

    }
}
