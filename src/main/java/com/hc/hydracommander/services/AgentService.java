package com.hc.hydracommander.services;

import com.hc.hydracommander.model.Agent;
import com.hc.hydracommander.repositories.AgentRepository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AgentService {

    @Autowired
    private AgentRepository repository;

    public Agent save(Agent agent) {
        if (agent.getId() != null) {
            Optional<Agent> existingAgent = repository.findById(agent.getId());
            if (existingAgent.isPresent()) {
                throw new IllegalStateException("Agent with this ID already exists.");
            }
        }

        return repository.saveAndFlush(agent);
    }

    public Agent update(UUID id, Agent agent) {
        if (id == null) {
            throw new IllegalArgumentException("ID must be provided for update.");
        }

        Optional<Agent> existingAgent = repository.findById(id);
        if (existingAgent.isEmpty()) {
            throw new IllegalStateException("Agent with given ID does not exist.");
        }

        agent.setId(id);
        return repository.saveAndFlush(agent);
    }

    public List<Agent> findAll() {
        return repository.findAll();
    }

    public Agent findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NoResultException("Ops! Not Found entity for this id! :("));
    }

    @Transactional
    public void delete(Agent agent) {
        repository.delete(agent);
    }

    @Transactional
    public void deleteById(UUID id) {
        Optional<Agent> agentToDelete = repository.findById(id);
        if (agentToDelete.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalStateException("Agent with given ID does not exist.");
        }
    }
}

