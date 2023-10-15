package com.aionpowerbook.aionupdateservice.update.service;

import com.aionpowerbook.aionupdateservice.update.entity.ClientUpdate;
import com.aionpowerbook.aionupdateservice.update.enums.UpdateStatus;
import com.aionpowerbook.aionupdateservice.update.repository.ClientUpdateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FileProcessorService {

    private final ClientUpdateRepository clientUpdateRepository;

    @Async
    public void processFiles(ClientUpdate clientUpdate) {
        try {
            log.info("sleeping...");
            Thread.sleep(10000);
            log.info("waking up");
            clientUpdateRepository.updateStatus(clientUpdate.getId(), UpdateStatus.SUCCESS);
        } catch (InterruptedException e) {
            //do nothing
        }
    }
}
