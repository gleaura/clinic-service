package com.gleaura.clinic.service.repository;

import com.gleaura.clinic.service.entity.TreatmentEntity;
import com.gleaura.clinic.service.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<TreatmentEntity, Long> {

    Page<TreatmentEntity> findAllByStatus(Status status, Pageable pageable);

    Page<TreatmentEntity> findAllByAppointment_Id(Long appointmentId, Pageable pageable);
}
