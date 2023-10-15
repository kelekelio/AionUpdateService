package com.aionpowerbook.aionupdateservice.update.service;

import com.aionpowerbook.aionupdateservice.update.dto.UpdateRequest;
import com.aionpowerbook.aionupdateservice.update.entity.ClientUpdate;
import com.aionpowerbook.aionupdateservice.update.repository.ClientUpdateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ClientUpdateService {

    private final ClientUpdateRepository clientUpdateRepository;

    public List<ClientUpdate> getUpdateForClient(Long id) {
        return clientUpdateRepository.findAllForClientId(id);
    }

    @Transactional
    public ClientUpdate requestUpdate(UpdateRequest request, Long id) {
        log.info("An update for the Client {} containing {} file to be updated to version {} has been requested.", id, request.getFiles().size(), request.getClientVersion());
        ClientUpdate update = ClientUpdate.of(request, id);
        return clientUpdateRepository.save(update);
    }
}
