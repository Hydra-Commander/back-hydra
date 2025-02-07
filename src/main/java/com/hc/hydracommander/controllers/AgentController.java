package com.hc.hydracommander.controllers;

import com.hc.hydracommander.model.Agent;
import com.hc.hydracommander.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping
    public ResponseEntity<Agent> save(@RequestBody Agent agent) {
        // Salva o agente chamando o método save
        Agent savedAgent = agentService.save(agent);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAgent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agent> update(@PathVariable UUID id, @RequestBody Agent agent) {
        Agent updatedAgent = agentService.update(id, agent);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAgent);
    }

    @GetMapping
    public ResponseEntity<List<Agent>> findAll() {
        List<Agent> agents = agentService.findAll();
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agent> findById(@PathVariable UUID id) {
        Agent agent = agentService.findById(id);
        return ResponseEntity.ok(agent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        agentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


