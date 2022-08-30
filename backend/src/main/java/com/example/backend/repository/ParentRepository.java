package com.example.backend.repository;

import com.example.backend.model.Branch;
import com.example.backend.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Long> {
}
