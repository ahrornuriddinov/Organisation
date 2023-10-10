package com.backend.organisation.entity.dto.req;

import com.backend.organisation.entity.enums.ProductType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaveRequest {
    @NotNull
    private String name;
    @NotNull
    private String code;
    @NotNull
    private ProductType type;
    @NotNull
    private UUID wareHouseUUID;
}
