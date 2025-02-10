package com.hc.hydracommander.controller.VO;

import java.time.LocalDate;

public record AgentDTO(
        String agentId,
        String hostname,
        String ipAddress,
        LocalDate lastActive
) {
}
