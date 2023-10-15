package com.aionpowerbook.aionupdateservice.update.dto;

import com.aionpowerbook.aionupdateservice.update.entity.ClientUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateDto {

    private Long id;
    private LocalDateTime timestamp;

    public static List<ClientUpdateDto> of(List<ClientUpdate> updates) {
        return updates.stream()
                .map(ClientUpdateDto::of)
                .toList();
    }

    public static ClientUpdateDto of(ClientUpdate update) {
        return ClientUpdateDto.builder()
                .id(update.getId())
                .timestamp(update.getTimestamp())
                .build();
    }
}
