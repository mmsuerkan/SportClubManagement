package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "club")
@Data
@NoArgsConstructor
public class Club {

    @Id
    @GeneratedValue
    private Long id;
    private String name;


    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Branch> branches;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Coordinator> coordinators;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Club club = (Club) o;
        return id != null && Objects.equals(id, club.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
