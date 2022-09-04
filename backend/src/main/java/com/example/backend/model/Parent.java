package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "parent")
@Data
@NoArgsConstructor
public class Parent {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @ManyToMany(mappedBy = "parents")
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Parent parent = (Parent) o;
        return id != null && Objects.equals(id, parent.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
