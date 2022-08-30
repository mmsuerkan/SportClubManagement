package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "trainer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "coordinator_id", nullable = false)
    private Coordinator coordinator;

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
