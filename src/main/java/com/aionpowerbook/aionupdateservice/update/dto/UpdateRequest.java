package com.aionpowerbook.aionupdateservice.update.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {

    @NotNull
    private String clientVersion;

    @NotEmpty
    private List<@Valid UpdateFileRequest> files = new ArrayList<>();
}
