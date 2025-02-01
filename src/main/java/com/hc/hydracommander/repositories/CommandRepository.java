package com.hc.hydracommander.repositories;

import com.hc.hydracommander.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRepository extends JpaRepository<Command, Long> {
}
