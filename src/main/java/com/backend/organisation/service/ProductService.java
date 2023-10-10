package com.backend.organisation.service;

import com.backend.organisation.entity.Product;
import com.backend.organisation.entity.dto.req.ProductSaveRequest;
import com.backend.organisation.entity.dto.req.ProductUpdateRequest;
import com.backend.organisation.entity.dto.res.ProductDto;
import com.backend.organisation.entity.model.PageAbleResponse;
import com.backend.organisation.entity.model.SavedResponse;
import com.backend.organisation.exceptions.CustomException;
import com.backend.organisation.repository.ProductRepository;
import com.backend.organisation.repository.WareHouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final WareHouseRepository wareHouseRepository;

    public PageAbleResponse<ProductDto> getAll(Optional<Integer> page, Optional<Integer> size) {
        int i = size.orElse(10) > 50 ? 10 : size.orElse(10);
        Page<Product> all = productRepository.findAllBy(PageRequest.of(page.orElse(1) - 1, i));
        return new PageAbleResponse<>(all.stream().map(e->modelMapper.map(e,ProductDto.class)).collect(Collectors.toList()),all.getTotalElements());
    }
    public ProductDto getOne(UUID id){
        Product product = productRepository.findById(id).orElseThrow(()->new CustomException(400,5001,"No Product found"));
        return modelMapper.map(product,ProductDto.class);
    }

    @Transactional
    public SavedResponse create(ProductSaveRequest request) {
        Optional<Product> byCode = productRepository.findByCode(request.getCode());
        if (byCode.isPresent()){
            throw new CustomException(400,5000,"this product is already exist");
        }
        ProductDto productDto = new ProductDto(null, request.getName(), request.getCode(), request.getType(),
                wareHouseRepository.findById(request.getWareHouseUUID())
                        .orElseThrow(() -> new CustomException(400, 5001, "No WareHouse found")));

        Product product = modelMapper.map(productDto, Product.class);
        Product save = productRepository.save(product);
        return new SavedResponse(save.getId());
    }
    @Transactional
    public SavedResponse update(ProductUpdateRequest request){
        ProductDto productDto = new ProductDto(request.getId(),
                request.getName(),
                request.getCode(),
                request.getType(),
                wareHouseRepository.findById(request.getWareHouseUUID()).orElseThrow(()->new CustomException(400,5001,"No WareHouse found")));
        Product mapped = modelMapper.map(productDto, Product.class);
        productRepository.save(mapped);
        return new SavedResponse(mapped.getId());
    }

    @Transactional
    public SavedResponse delete(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomException(400, 5001, "No Product found"));
        productRepository.deleteById(product.getId());
        return new SavedResponse(product.getId());
    }
}
