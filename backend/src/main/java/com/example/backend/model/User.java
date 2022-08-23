package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

}
