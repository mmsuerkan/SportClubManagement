package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "coordinator")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "coordinator", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Trainer> trainers = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }
}
