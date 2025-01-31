package com.hc.hydracommander.repositories;

import com.hc.hydracommander.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AgentRepository extends JpaRepository<Agent, UUID> {

}
