package com.backend.organisation.controller;

import com.backend.organisation.entity.dto.req.WareHouseSaveRequest;
import com.backend.organisation.entity.dto.req.WareHouseUpdateRequest;
import com.backend.organisation.entity.dto.res.ProductDto;
import com.backend.organisation.entity.dto.res.WareHouseDto;
import com.backend.organisation.entity.model.PageAbleResponse;
import com.backend.organisation.entity.model.SavedResponse;
import com.backend.organisation.service.WareHouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/wareHouses")
@RequiredArgsConstructor
public class WareHouseController {
    private final WareHouseService wareHouseService;

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping
    public ResponseEntity<PageAbleResponse<WareHouseDto>> getAll(@RequestParam Optional<Integer> page,
                                                                 @RequestParam Optional<Integer> size){
        return ResponseEntity.ok(wareHouseService.getAll(page,size));
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<WareHouseDto> getOne(@PathVariable UUID id){
        return ResponseEntity.ok(wareHouseService.getOne(id));
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @PostMapping
    public ResponseEntity<SavedResponse> creat(@RequestBody @Valid WareHouseSaveRequest request){
        return ResponseEntity.ok(wareHouseService.create(request));
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @PutMapping
    public ResponseEntity<SavedResponse> update(@RequestBody @Valid WareHouseUpdateRequest wareHouseUpdateRequest){
        return ResponseEntity.ok(wareHouseService.update(wareHouseUpdateRequest));
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<SavedResponse> delete(@PathVariable UUID id){
        return ResponseEntity.ok(wareHouseService.delete(id));
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping("/{id}/products")
    public ResponseEntity<PageAbleResponse<ProductDto>> getAllWareHouses(@PathVariable UUID id,
                                                                         @RequestParam Optional<Integer> page,
                                                                         @RequestParam Optional<Integer> size){
        return ResponseEntity.ok(wareHouseService.getAllProducts(id,page,size));
    }
}
