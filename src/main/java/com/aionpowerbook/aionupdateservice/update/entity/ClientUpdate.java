package com.aionpowerbook.aionupdateservice.update.entity;

import com.aionpowerbook.aionupdateservice.update.dto.UpdateRequest;
import com.aionpowerbook.aionupdateservice.update.enums.UpdateStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ClientUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long aionClientId;

    private String clientVersion;

    @Enumerated(EnumType.STRING)
    private UpdateStatus updateStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientUpdate", cascade = CascadeType.ALL)
    @OrderBy("createdDateTime desc")
    private List<ClientFile> files = new ArrayList<>();

    @NotNull
    @CreatedDate
    private LocalDateTime createdDateTime;

    @NotNull
    @LastModifiedDate
    private LocalDateTime lastModifiedDateTime;

    @NotNull
    @Version
    private Integer version;

    public static ClientUpdate of(UpdateRequest updateRequest, Long id) {
        ClientUpdate update = ClientUpdate.builder()
                .clientVersion(updateRequest.getClientVersion())
                .aionClientId(id)
                .updateStatus(UpdateStatus.PROCESSING)
                .build();
        update.setFiles(ClientFile.of(updateRequest.getFiles(), update));
        return update;
    }
}
