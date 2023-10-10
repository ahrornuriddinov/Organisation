package com.backend.organisation.repository;

import com.backend.organisation.entity.WareHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouse, UUID> {
    Page<WareHouse> findAllBy(Pageable pageable);
    Optional<WareHouse> findByLocation(String location);

    boolean existsByOrganisationId(UUID uuid);

    Page<WareHouse> findAllByOrganisationId(UUID id,Pageable pageable);
}
