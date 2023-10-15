package com.aionpowerbook.aionupdateservice.update.entity;

import com.aionpowerbook.aionupdateservice.update.dto.UpdateFileRequest;
import com.aionpowerbook.aionupdateservice.update.enums.FileExtension;
import com.aionpowerbook.aionupdateservice.update.enums.UpdateStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ClientFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClientUpdate clientUpdate;

    @Enumerated(EnumType.STRING)
    private UpdateStatus updateStatus;

    private String absolutePath;

    private String path;

    @Enumerated(EnumType.STRING)
    private FileExtension fileExtension;

    @NotNull
    @CreatedDate
    private LocalDateTime createdDateTime;

    @NotNull
    @LastModifiedDate
    private LocalDateTime lastModifiedDateTime;

    @NotNull
    @Version
    private Integer version;

    public static List<ClientFile> of(List<UpdateFileRequest> filesRequests, ClientUpdate clientUpdate) {
        return filesRequests.parallelStream()
                .map(request -> of(request, clientUpdate))
                .toList();
    }

    public static ClientFile of(UpdateFileRequest request, ClientUpdate clientUpdate) {
        return ClientFile.builder()
                .absolutePath(request.getAbsolutePath())
                .path(request.getPath())
                .fileExtension(request.getExtension())
                .clientUpdate(clientUpdate)
                .updateStatus(UpdateStatus.PROCESSING)
                .build();
    }

}
