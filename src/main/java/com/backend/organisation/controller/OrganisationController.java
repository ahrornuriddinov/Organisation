package com.backend.organisation.controller;

import com.backend.organisation.entity.dto.OrganisationDto;
import com.backend.organisation.entity.dto.res.WareHouseDto;
import com.backend.organisation.entity.model.PageAbleResponse;
import com.backend.organisation.entity.model.SavedResponse;
import com.backend.organisation.service.OrganisationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/organisations")
@RequiredArgsConstructor
public class OrganisationController {
    private final OrganisationService organisationService;

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping
    public ResponseEntity<PageAbleResponse<OrganisationDto>> getAll(@RequestParam Optional<Integer> page,
                                                                    @RequestParam Optional<Integer> size){
        return ResponseEntity.ok(organisationService.getAll(page, size));
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrganisationDto> getOne(@PathVariable UUID id){
        return ResponseEntity.ok(organisationService.getOne(id));
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @PostMapping
    public ResponseEntity<SavedResponse> creat(@RequestBody @Valid OrganisationDto organisationDto){
        return ResponseEntity.ok(organisationService.create(organisationDto));
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @PutMapping
    public ResponseEntity<SavedResponse> update(@RequestBody @Valid OrganisationDto organisationDto){
        return ResponseEntity.ok(organisationService.update(organisationDto));
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<SavedResponse> delete(@PathVariable UUID id){
        return ResponseEntity.ok(organisationService.delete(id));
    }
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping("/{id}/wareHouses")
    public ResponseEntity<PageAbleResponse<WareHouseDto>> getAllWareHouses(@PathVariable UUID id,
                                                                           @RequestParam Optional<Integer> page,
                                                                           @RequestParam Optional<Integer> size){
        return ResponseEntity.ok(organisationService.getAllWareHouses(id,page,size));
    }
}
