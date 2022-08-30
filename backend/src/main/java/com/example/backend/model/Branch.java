package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "branch")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;


    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;


    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Trainer> trainers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Group> groups = new LinkedHashSet<>();

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }


    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
