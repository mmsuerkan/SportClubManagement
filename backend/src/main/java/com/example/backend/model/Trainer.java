package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "trainer")
@Data
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "coordinator_id", insertable = false, updatable = false)
    @JsonBackReference
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "coordinator_id", insertable = false, updatable = false)
    @JsonBackReference
    private Coordinator coordinator;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Trainer trainer = (Trainer) o;
        return id != null && Objects.equals(id, trainer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
