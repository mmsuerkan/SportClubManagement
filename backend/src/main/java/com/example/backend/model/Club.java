package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "club")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Branch> branches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Coordinator> coordinators = new LinkedHashSet<>();

    public Set<Coordinator> getCoordinators() {
        return coordinators;
    }

    public void setCoordinators(Set<Coordinator> coordinators) {
        this.coordinators = coordinators;
    }


    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }
}
