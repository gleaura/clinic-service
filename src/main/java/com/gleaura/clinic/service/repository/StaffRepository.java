package com.gleaura.clinic.service.repository;

import com.gleaura.clinic.service.entity.StaffEntity;
import com.gleaura.clinic.service.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    Page<StaffEntity> findAllByStatus(Status status, Pageable pageable);
}
