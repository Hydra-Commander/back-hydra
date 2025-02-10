package com.hc.hydracommander.controller;

import com.hc.hydracommander.controller.VO.AgentDTO;
import com.hc.hydracommander.model.Agent;
import com.hc.hydracommander.service.AgentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping
    public ResponseEntity<Agent> save(@RequestBody AgentDTO model) {
        Agent agent = new Agent();
        BeanUtils.copyProperties(model, agent);
        Agent savedAgent = agentService.save(agent);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAgent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agent> update(@PathVariable Long id, @RequestBody Agent agent) {
        Agent updatedAgent = agentService.update(id, agent);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAgent);
    }

    @GetMapping
    public ResponseEntity<List<Agent>> findAll() {
        List<Agent> agents = agentService.findAll();
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agent> findById(@PathVariable Long id) {
        Agent agent = agentService.findById(id);
        return ResponseEntity.ok(agent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        agentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


