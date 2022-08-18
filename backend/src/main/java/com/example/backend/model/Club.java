package com.example.backend.model;

import lombok.*;
import javax.persistence.*;
import java.util.LinkedHashSet;

import java.util.Set;

@Entity
@Table(name = "club")
@Data
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private String branchName;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new LinkedHashSet<>();


}
