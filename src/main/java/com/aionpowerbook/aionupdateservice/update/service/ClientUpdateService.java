package com.aionpowerbook.aionupdateservice.update.service;

import com.aionpowerbook.aionupdateservice.aionlog.enums.LogType;
import com.aionpowerbook.aionupdateservice.aionlog.service.ClientLogsService;
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

    private final FileProcessorService fileProcessorService;
    private final ClientLogsService clientLogsService;

    public List<ClientUpdate> getUpdateForClient(Long id) {
        return clientUpdateRepository.findAllForClientId(id);
    }

    @Transactional
    public ClientUpdate requestUpdate(UpdateRequest request, Long id) {
        log.info("An update for the Client {} containing {} files to be updated to version {} has been requested.", id, request.getFiles().size(), request.getClientVersion());
        clientLogsService.saveLog(id, LogType.INFO, String.format("Requesting %d Client to be updated to version: %s", id, request.getClientVersion()));
        ClientUpdate update = ClientUpdate.of(request, id);
        ClientUpdate saved = clientUpdateRepository.save(update);
        fileProcessorService.processFiles(saved);
        return saved;
    }
}
