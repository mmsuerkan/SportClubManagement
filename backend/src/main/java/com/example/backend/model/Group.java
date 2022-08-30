package com.example.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@Data
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String groupName;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Student> students = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

}
