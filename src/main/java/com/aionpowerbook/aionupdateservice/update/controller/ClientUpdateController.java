package com.aionpowerbook.aionupdateservice.update.controller;

import com.aionpowerbook.aionupdateservice.update.dto.ClientUpdateDto;
import com.aionpowerbook.aionupdateservice.update.dto.UpdateRequest;
import com.aionpowerbook.aionupdateservice.update.entity.ClientUpdate;
import com.aionpowerbook.aionupdateservice.update.service.ClientUpdateService;
import com.aionpowerbook.aionupdateservice.utils.ServerResolverUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/updates")
@AllArgsConstructor
@Slf4j
public class ClientUpdateController {

    private final ClientUpdateService clientUpdateService;

    @GetMapping
    public List<ClientUpdateDto> getUpdate() {
        Long id = ServerResolverUtil.resolveServerId();
        List<ClientUpdate> update = clientUpdateService.getUpdateForClient(id);
        log.info("Found {} updates for Client {}", update.size(), id);
        return ClientUpdateDto.of(update);
    }

    @PostMapping
    public ClientUpdateDto requestUpdate(@Valid @RequestBody UpdateRequest request) {
        Long id = ServerResolverUtil.resolveServerId();
        ClientUpdate update = clientUpdateService.requestUpdate(request, id);
        return ClientUpdateDto.of(update);
    }
}
