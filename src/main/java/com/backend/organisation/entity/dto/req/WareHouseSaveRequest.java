package com.backend.organisation.entity.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseSaveRequest {
    @NotNull
    private String name;
    @NotNull
    private String location;
    @NotNull
    private UUID organisationUUID;
}
