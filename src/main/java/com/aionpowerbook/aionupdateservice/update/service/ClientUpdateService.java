package com.aionpowerbook.aionupdateservice.update.service;

import com.aionpowerbook.aionupdateservice.update.dto.UpdateRequest;
import com.aionpowerbook.aionupdateservice.update.entity.ClientUpdate;
import com.aionpowerbook.aionupdateservice.update.repository.ClientUpdateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ClientUpdateService {

    private final ClientUpdateRepository clientUpdateRepository;

    public List<ClientUpdate> getUpdateForClient(Long id) {
        return clientUpdateRepository.findAllForClientId(id);
    }

    public ClientUpdate requestUpdate(UpdateRequest request, Long id) {
        if (!Objects.equals(request.getClientId(), id)) {
            log.error("Requested an update for client {} but server is set to {}", request.getClientId(), id);
            throw new IllegalArgumentException("Wrong client id");
        }
        log.info("An update for the Client {} has been requested.", id);
        ClientUpdate update = ClientUpdate.of(request, id);
        return clientUpdateRepository.save(update);
    }
}
