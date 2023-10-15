package com.aionpowerbook.aionupdateservice.aionlog.dto;

import com.aionpowerbook.aionupdateservice.aionlog.entity.ClientLog;
import com.aionpowerbook.aionupdateservice.aionlog.enums.LogType;
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
public class ClientLogDto {
    private Long id;
    private LogType logType;
    private String message;
    private LocalDateTime timestamp;

    public static List<ClientLogDto> of(List<ClientLog> logs) {
        if (logs == null) {
            return new ArrayList<>();
        }

        return logs.stream()
                .map(ClientLogDto::of)
                .toList();
    }
    public static ClientLogDto of(ClientLog log) {
        return ClientLogDto.builder()
                .id(log.getId())
                .logType(log.getLogType())
                .message(log.getMessage())
                .timestamp(log.getTimestamp())
                .build();
    }
}
