package com.hc.hydracommander.controller;

import com.hc.hydracommander.controller.VO.CreateCommandDTO;
import com.hc.hydracommander.controller.VO.SendCommandDTO;
import com.hc.hydracommander.model.Agent;
import com.hc.hydracommander.model.Command;
import com.hc.hydracommander.model.CommandResult;
import com.hc.hydracommander.model.User;
import com.hc.hydracommander.repository.AgentRepository;
import com.hc.hydracommander.repository.CommandRepository;
import com.hc.hydracommander.service.AgentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commands")
public class CommandController {
    private final CommandRepository commandRepository;
    private final AgentRepository agentRepository;

    @Autowired
    private AgentService agentService;

    public CommandController(
        CommandRepository commandRepository,
        AgentRepository agentRepository
    ) {
        this.commandRepository = commandRepository;
        this.agentRepository = agentRepository;
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

    @PostMapping
    public ResponseEntity<Object> sendCommand(SendCommandDTO model){
        Optional<Command> command = commandRepository.findById(model.commandId());
        if (command.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Command not found");
        }

        Optional<Agent> agent = agentRepository.findById(model.agentId());
        if (agent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agent not found");
        }

        Command commandEntity = command.get();
        Agent agentEntity = agent.get();

        try{
            boolean success = this.agentService.sendCommand(agentEntity, commandEntity);
            if (success) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Command sent successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send command");
            }
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send command " + e.getMessage());
        }
    }
}
