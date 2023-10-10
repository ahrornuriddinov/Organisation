package com.backend.organisation.repository;

import com.backend.organisation.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findAllBy(Pageable pageable);

    Optional<Product> findByCode(String code);

    Page<Product> findAllByWareHouseId(UUID id,Pageable pageable);
}
