package com.hc.hydracommander.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Agent")
@Table(name = "agents")
@Data
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String hostname;
    private String ipAdress;
    //private StatusAgent status;
    private LocalDate lastActive;

}
