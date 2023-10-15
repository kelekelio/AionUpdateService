package com.aionpowerbook.aionupdateservice.aionlog.controller;

import com.aionpowerbook.aionupdateservice.aionlog.dto.ClientLogDto;
import com.aionpowerbook.aionupdateservice.aionlog.entity.ClientLog;
import com.aionpowerbook.aionupdateservice.aionlog.service.ClientLogsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
@Slf4j
public class ClientLogController {

    private final ClientLogsService clientLogsService;

    @GetMapping("/{id}")
    public List<ClientLogDto> findAllForClient(@PathVariable Long id) {
        List<ClientLog> logs = clientLogsService.findForClient(id);
        return logs.stream()
                .map(ClientLogDto::of)
                .toList();

    }
}
