package com.aionpowerbook.aionupdateservice.aionlog.entity;

import com.aionpowerbook.aionupdateservice.aionlog.enums.LogType;
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
public class ClientLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long aionClientId;

    @Enumerated(EnumType.STRING)
    private LogType logType;

    private String message;

    private LocalDateTime timestamp;
}
