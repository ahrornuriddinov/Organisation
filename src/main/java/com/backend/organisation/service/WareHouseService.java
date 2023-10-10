package com.backend.organisation.service;

import com.backend.organisation.entity.Organisation;
import com.backend.organisation.entity.Product;
import com.backend.organisation.entity.WareHouse;
import com.backend.organisation.entity.dto.req.WareHouseSaveRequest;
import com.backend.organisation.entity.dto.req.WareHouseUpdateRequest;
import com.backend.organisation.entity.dto.res.ProductDto;
import com.backend.organisation.entity.dto.res.WareHouseDto;
import com.backend.organisation.entity.model.PageAbleResponse;
import com.backend.organisation.entity.model.SavedResponse;
import com.backend.organisation.exceptions.CustomException;
import com.backend.organisation.repository.OrganisationRepository;
import com.backend.organisation.repository.ProductRepository;
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
public class WareHouseService {
    private final WareHouseRepository wareHouseRepository;
    private final ModelMapper modelMapper;
    private final OrganisationRepository organisationRepository;
    private final ProductRepository productRepository;

    public PageAbleResponse<WareHouseDto> getAll(Optional<Integer> page, Optional<Integer> size) {
        int i = size.orElse(10) > 50 ? 10 : size.orElse(10);
        Page<WareHouse> allBy = wareHouseRepository.findAllBy(PageRequest.of(page.orElse(1) - 1, i));
        return new PageAbleResponse<>(allBy.stream().map(e -> modelMapper.map(e, WareHouseDto.class)).collect(Collectors.toList()), allBy.getTotalElements());
    }

    public WareHouseDto getOne(UUID id) {
        Optional<WareHouse> byId = wareHouseRepository.findById(id);
        byId.orElseThrow(() -> new CustomException(400, 5001, "No WareHOuse found"));
        return modelMapper.map(byId, WareHouseDto.class);
    }

    @Transactional
    public SavedResponse create(WareHouseSaveRequest request) {
        Optional<WareHouse> location = wareHouseRepository.findByLocation(request.getLocation());
        if (location.isPresent()) {
            throw new CustomException(400, 5000, "This WareHouse is already exist");
        }
        WareHouseDto wareHouseDto = new WareHouseDto(null, request.getName(), request.getLocation(),
                organisationRepository.findById(request.getOrganisationUUID()).orElseThrow(()->new CustomException(400,5001,"No Organisation found")));

        WareHouse wareHouse = modelMapper.map(wareHouseDto, WareHouse.class);
        WareHouse save = wareHouseRepository.save(wareHouse);
        return new SavedResponse(save.getId());
    }

    @Transactional
    public SavedResponse update(WareHouseUpdateRequest request) {
        WareHouseDto wareHouseDto = new WareHouseDto(request.getId(),
                request.getName(),
                request.getLocation(),
                organisationRepository.findById(request.getOrganisationUUID()).orElseThrow(() -> new CustomException(400, 5001, "No Organisation found")));
        WareHouse mapped = modelMapper.map(wareHouseDto, WareHouse.class);
        wareHouseRepository.save(mapped);
        return new SavedResponse(mapped.getId());
    }

    @Transactional
    public SavedResponse delete(UUID id) {
        WareHouse wareHouse = wareHouseRepository.findById(id).orElseThrow(() -> new CustomException(400, 5001, "No WareHouse found"));
        wareHouseRepository.deleteById(wareHouse.getId());
        return new SavedResponse(wareHouse.getId());
    }

    public PageAbleResponse<ProductDto> getAllProducts(UUID id, Optional<Integer> page, Optional<Integer> size) {
        int i = size.orElse(10) > 50 ? 10 : size.orElse(10);
        Page<Product> allProducts = productRepository.findAllByWareHouseId(id,PageRequest.of(page.orElse(1) - 1, i));
        List<ProductDto> productsDtos = allProducts.stream().map(e -> modelMapper.map(e, ProductDto.class)).toList();
        return new PageAbleResponse<>(productsDtos,allProducts.getTotalElements());
    }
}
