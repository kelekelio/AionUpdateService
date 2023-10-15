package com.aionpowerbook.aionupdateservice.update.dto;

import com.aionpowerbook.aionupdateservice.update.enums.FileExtension;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFileRequest {

    @NotNull
    private String absolutePath;

    @NotNull
    private String path;

    @NotNull
    private FileExtension extension;
}
