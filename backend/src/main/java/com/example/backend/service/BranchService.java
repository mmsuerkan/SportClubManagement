package com.example.backend.service;

import com.example.backend.model.Branch;
import com.example.backend.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> listAllBranch(){
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long id){
        return branchRepository.findById(id).get();
    }

    public void deleteBranchById(Long id){
        branchRepository.deleteById(id);
    }

    public void addBranch(Branch newBranch){
        branchRepository.save(newBranch);
    }

    public void updateBranch(Long updatedBranchId,Branch updatedBranch){

        Branch oldBranch = branchRepository.findById(updatedBranch.getId()).get();
        updatedBranch.setId(oldBranch.getId());
        branchRepository.save(updatedBranch);

    }


}
