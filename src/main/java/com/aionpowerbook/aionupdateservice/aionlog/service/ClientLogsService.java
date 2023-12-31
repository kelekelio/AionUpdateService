package com.aionpowerbook.aionupdateservice.aionlog.service;

import com.aionpowerbook.aionupdateservice.aionlog.entity.ClientLog;
import com.aionpowerbook.aionupdateservice.aionlog.enums.LogType;
import com.aionpowerbook.aionupdateservice.aionlog.repository.ClientLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientLogsService {

    private final ClientLogRepository clientLogRepository;

    public List<ClientLog> findForClient(Long id) {
        return clientLogRepository.findAllForClientId(id);
    }

    public void saveLog(Long clientId, LogType logType, String message) {
        ClientLog log = ClientLog.builder()
                .aionClientId(clientId)
                .logType(logType)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
        clientLogRepository.save(log);
    }
}
