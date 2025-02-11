package com.hc.hydracommander.service;

import com.hc.hydracommander.model.Agent;
import com.hc.hydracommander.model.Command;
import com.hc.hydracommander.repository.AgentRepository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    private static final String baseURLAgent = "URL_BASE_DOS_AGENTES/";
    @Autowired
    private AgentRepository repository;
    private RestTemplate restTemplate;

    public Agent save(Agent agent) {
        if (agent.getId() != null) {
            Optional<Agent> existingAgent = repository.findById(agent.getId());
            if (existingAgent.isPresent()) {
                throw new IllegalStateException("Agent with this ID already exists.");
            }
        }

        return repository.saveAndFlush(agent);
    }

    public Agent update(Long id, Agent agent) {
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

    public Agent findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoResultException("Ops! Not Found entity for this id! :("));
    }

    @Transactional
    public void delete(Agent agent) {
        repository.delete(agent);
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Agent> agentToDelete = repository.findById(id);
        if (agentToDelete.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalStateException("Agent with given ID does not exist.");
        }
    }

    public boolean sendCommand(Agent agent, Command command) {
        try {
            ResponseEntity<Boolean> response = restTemplate.postForEntity(
                    baseURLAgent + agent.getAgentId(),
                    command,
                    Boolean.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            } else {
                throw new RuntimeException("Resposta inválida do servidor: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Erro ao enviar comando: " + e.getStatusCode(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao enviar comando", e);
        }
    }
}

