package com.hc.hydracommander.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity(name = "Command")
@Table(name = "commands")
@Data
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String commandId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private boolean isCustom;

    @Column(columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Parameter> parameters;
}