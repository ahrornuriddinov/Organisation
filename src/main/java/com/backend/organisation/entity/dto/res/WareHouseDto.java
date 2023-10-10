package com.backend.organisation.entity.dto.res;

import com.backend.organisation.entity.Organisation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WareHouseDto {
    private UUID id;
    private String name;
    private String location;
    private Organisation organisation;
}
