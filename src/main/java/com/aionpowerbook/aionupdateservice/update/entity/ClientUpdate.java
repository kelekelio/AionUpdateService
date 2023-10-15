package com.aionpowerbook.aionupdateservice.update.entity;

import com.aionpowerbook.aionupdateservice.update.dto.UpdateRequest;
import com.aionpowerbook.aionupdateservice.update.enums.UpdateStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long aionClientId;

    @Enumerated(EnumType.STRING)
    private UpdateStatus updateStatus;

    private LocalDateTime timestamp;

    public static ClientUpdate of(UpdateRequest updateRequest, Long id) {
        return ClientUpdate.builder()
                .aionClientId(id)
                .updateStatus(UpdateStatus.PROCESSING)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
