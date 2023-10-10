package com.backend.organisation.service;

import com.backend.organisation.entity.Organisation;
import com.backend.organisation.entity.WareHouse;
import com.backend.organisation.entity.dto.OrganisationDto;
import com.backend.organisation.entity.dto.res.WareHouseDto;
import com.backend.organisation.entity.model.PageAbleResponse;
import com.backend.organisation.entity.model.SavedResponse;
import com.backend.organisation.exceptions.CustomException;
import com.backend.organisation.repository.OrganisationRepository;
import com.backend.organisation.repository.WareHouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganisationService {
    private final OrganisationRepository organisationRepository;
    private final ModelMapper modelMapper;
    private final WareHouseRepository wareHouseRepository;
    public PageAbleResponse<OrganisationDto> getAll(Optional<Integer> page, Optional<Integer> size) {
        int i = size.orElse(10) > 50 ? 10 : size.orElse(10);
        Page<Organisation> all = organisationRepository.findAllBy(PageRequest.of(page.orElse(1) - 1, i));
        return new PageAbleResponse<>(all.stream().map(e->modelMapper.map(e, OrganisationDto.class)).collect(Collectors.toList()),all.getTotalElements());
    }

    public OrganisationDto getOne(UUID id) {
        Organisation organisation = organisationRepository.findById(id).orElseThrow(() -> new CustomException(400, 5001, "No Organisation found"));
        return modelMapper.map(organisation,OrganisationDto.class);
    }


    @Transactional
    public SavedResponse create(OrganisationDto organisationDto) {
        Optional<Organisation> name = organisationRepository.findByName(organisationDto.getName());
        if (name.isPresent()){
            throw new CustomException(400,5000,"this organisation is already exist");
        }
        Organisation map = modelMapper.map(organisationDto, Organisation.class);
        Organisation save = organisationRepository.save(map);
        return new SavedResponse(save.getId());
    }
    @Transactional
    public SavedResponse update(OrganisationDto organisationDto){
        Organisation organisation = organisationRepository.findById(organisationDto.getId()).orElseThrow(() -> new CustomException(400, 5001, "No Organisation found"));
        Organisation mapped = modelMapper.map(organisationDto, Organisation.class);
        organisationRepository.save(mapped);
        return new SavedResponse(organisation.getId());
    }
    @Transactional
    public SavedResponse delete(UUID id) {
        Organisation organisation = organisationRepository.findById(id).orElseThrow(() -> new CustomException(400, 5001, "No Organisation found"));
        if (wareHouseRepository.existsByOrganisationId(organisation.getId())){
            throw new CustomException(400,5002,"Organisation has warehouses");
        }
        organisationRepository.deleteById(organisation.getId());
        return new SavedResponse(organisation.getId());
    }
    public PageAbleResponse<WareHouseDto> getAllWareHouses(UUID id,Optional<Integer> page, Optional<Integer> size) {
        int i = size.orElse(10) > 50 ? 10 : size.orElse(10);
        Page<WareHouse> allWareHouses = wareHouseRepository.findAllByOrganisationId(id,PageRequest.of(page.orElse(1) - 1, i));
        List<WareHouseDto> wareHouseDtos = allWareHouses.stream().map(e -> modelMapper.map(e, WareHouseDto.class)).toList();
        return new PageAbleResponse<>(wareHouseDtos,allWareHouses.getTotalElements());
    }
}
