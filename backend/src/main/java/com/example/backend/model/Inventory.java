package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "inventory", cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private Branch branch;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
