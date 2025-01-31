package com.hc.hydracommander.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;
    private String password;
}
