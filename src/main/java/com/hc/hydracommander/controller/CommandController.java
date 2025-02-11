package com.hc.hydracommander.controller;

import com.hc.hydracommander.controller.VO.CreateCommandDTO;
import com.hc.hydracommander.model.Command;
import com.hc.hydracommander.repository.CommandRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commands")
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
