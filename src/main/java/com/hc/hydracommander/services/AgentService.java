package com.hc.hydracommander.services;

import com.hc.hydracommander.model.Agent;
import com.hc.hydracommander.model.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AgentService {
    private static final String baseURL = "https://8218ad26-88c2-4a19-a37d-bef8479ed0a0.mock.pstmn.io";

    @Autowired
    private RestTemplate restTemplate;

    public Boolean sendCommand(Agent agent, Command command) {
        ResponseEntity<Boolean> response = restTemplate.postForEntity(baseURL, command, Boolean.class);
        return response.getBody();
    }
}
