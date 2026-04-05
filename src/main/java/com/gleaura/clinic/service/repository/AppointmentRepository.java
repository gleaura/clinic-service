package com.gleaura.clinic.service.repository;

import com.gleaura.clinic.service.entity.AppointmentEntity;
import com.gleaura.clinic.service.enums.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    Page<AppointmentEntity> findAllByStatus(AppointmentStatus status, Pageable pageable);

    Page<AppointmentEntity> findAllByPatient_Id(Long patientId, Pageable pageable);

    List<AppointmentEntity> findAllByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);
}
