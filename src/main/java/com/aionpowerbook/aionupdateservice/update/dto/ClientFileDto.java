package com.aionpowerbook.aionupdateservice.update.dto;

import com.aionpowerbook.aionupdateservice.update.entity.ClientFile;
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
public class ClientFileDto {
    private Long id;
    private UpdateStatus updateStatus;
    private LocalDateTime timestamp;

    public static List<ClientFileDto> of(List<ClientFile> files) {
        if (files == null) {
            return new ArrayList<>();
        }
        return files.stream()
                .map(ClientFileDto::of)
                .toList();
    }

    public static ClientFileDto of(ClientFile file) {
        return ClientFileDto.builder()
                .id(file.getId())
                .updateStatus(file.getUpdateStatus())
                .timestamp(file.getCreatedDateTime())
                .build();
    }
}
