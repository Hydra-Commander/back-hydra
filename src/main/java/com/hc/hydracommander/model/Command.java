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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}