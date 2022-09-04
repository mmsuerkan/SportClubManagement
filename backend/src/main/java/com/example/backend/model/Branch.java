package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "branch")
@Data
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Club club;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;


    @OneToMany
    @JoinColumn(name = "branch_id")
    @JsonManagedReference
    private Set<Trainer> trainers;

    @OneToMany
    @JoinColumn(name = "branch_id")
    @JsonManagedReference
    private Set<Group> groups;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Branch branch = (Branch) o;
        return id != null && Objects.equals(id, branch.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
