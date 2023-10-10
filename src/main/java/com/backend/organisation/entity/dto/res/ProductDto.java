package com.backend.organisation.entity.dto.res;

import com.backend.organisation.entity.WareHouse;
import com.backend.organisation.entity.enums.ProductType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private UUID id;
    private String name;
    private String code;
    private ProductType type;
    private WareHouse wareHouse;
}
