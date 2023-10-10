package com.backend.organisation.controller;

import com.backend.organisation.entity.dto.req.ProductSaveRequest;
import com.backend.organisation.entity.dto.req.ProductUpdateRequest;
import com.backend.organisation.entity.dto.res.ProductDto;
import com.backend.organisation.entity.model.PageAbleResponse;
import com.backend.organisation.entity.model.SavedResponse;
import com.backend.organisation.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<PageAbleResponse<ProductDto>> getAll(@RequestParam Optional<Integer> page,
                                                               @RequestParam Optional<Integer> size){
        return ResponseEntity.ok(productService.getAll(page, size));
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getOne(@PathVariable UUID id){
        return ResponseEntity.ok(productService.getOne(id));
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<SavedResponse> creat(@RequestBody @Valid ProductSaveRequest request){
        return ResponseEntity.ok(productService.create(request));
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<SavedResponse> update(@RequestBody @Valid ProductUpdateRequest productUpdateRequest){
        return ResponseEntity.ok(productService.update(productUpdateRequest));
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<SavedResponse> delete(@PathVariable UUID id){
        return ResponseEntity.ok(productService.delete(id));
    }
}
