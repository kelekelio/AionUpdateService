package com.aionpowerbook.aionupdateservice.update.dto;

import com.aionpowerbook.aionupdateservice.update.entity.ClientUpdate;
import com.aionpowerbook.aionupdateservice.update.enums.UpdateStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateDto {

    private Long id;
    private LocalDateTime timestamp;
    private UpdateStatus updateStatus;
    private List<ClientFileDto> files = new ArrayList<>();

    public static List<ClientUpdateDto> of(List<ClientUpdate> updates) {
        return updates.stream()
                .map(ClientUpdateDto::of)
                .toList();
    }

    public static ClientUpdateDto of(ClientUpdate update) {
        return ClientUpdateDto.builder()
                .id(update.getId())
                .updateStatus(update.getUpdateStatus())
                .files(ClientFileDto.of(update.getFiles()))
                .timestamp(update.getCreatedDateTime())
                .build();
    }
}
