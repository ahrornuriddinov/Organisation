package com.backend.organisation.repository;

import com.backend.organisation.entity.Organisation;
import com.backend.organisation.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, UUID> {
    Page<Organisation> findAllBy(Pageable pageable);
    Optional<Organisation> findByName(String name);

}
