package com.example.backend.repository;

import com.example.backend.model.Branch;
import com.example.backend.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
}
