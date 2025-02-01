package com.hc.hydracommander.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hc.hydracommander.dtos.CreateCommandDTO;
import com.hc.hydracommander.model.Command;
import com.hc.hydracommander.repositories.CommandRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/command")
public class CommandController {

    private final CommandRepository commandRepository;

    public CommandController(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Command> createCommand(@RequestBody @Valid CreateCommandDTO model) {
        Command command = new Command();
        BeanUtils.copyProperties(model, command);
        Command commandResponse = this.commandRepository.save(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(commandResponse);
    }

    @GetMapping
    public ResponseEntity<List<Command>> getAllCommands() {
        List<Command> commands = this.commandRepository.findAll();
        return ResponseEntity.ok(commands);
    }

}
