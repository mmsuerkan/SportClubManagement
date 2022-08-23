package com.example.backend.model;

import lombok.*;
import javax.persistence.*;
import java.util.LinkedHashSet;

import java.util.Set;

@Entity
@Table(name = "club")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String branchName;

}
