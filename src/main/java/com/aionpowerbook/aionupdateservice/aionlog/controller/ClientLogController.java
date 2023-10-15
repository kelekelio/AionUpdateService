package com.aionpowerbook.aionupdateservice.aionlog.controller;

import com.aionpowerbook.aionupdateservice.aionlog.dto.ClientLogDto;
import com.aionpowerbook.aionupdateservice.aionlog.entity.ClientLog;
import com.aionpowerbook.aionupdateservice.aionlog.service.ClientLogsService;
import com.aionpowerbook.aionupdateservice.utils.ServerResolverUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
@Slf4j
public class ClientLogController {

    private final ClientLogsService clientLogsService;

    @GetMapping
    public List<ClientLogDto> findAllForClient() {
        Long id = ServerResolverUtil.resolveServerId();
        List<ClientLog> logs = clientLogsService.findForClient(id);
        log.info("Found {} logs for client {}", logs.size(), id);
        return ClientLogDto.of(logs);

    }
}
