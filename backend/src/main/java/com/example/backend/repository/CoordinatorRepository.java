package com.example.backend.repository;

import com.example.backend.model.Club;
import com.example.backend.model.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator,Long> {
}
