package com.gleaura.clinic.service.repository;

import com.gleaura.clinic.service.entity.PatientEntity;
import com.gleaura.clinic.service.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Page<PatientEntity> findAllByStatus(Status status, Pageable pageable);

    boolean existsByIdentityNumber(String identityNumber);

    boolean existsByEmail(String email);
}
