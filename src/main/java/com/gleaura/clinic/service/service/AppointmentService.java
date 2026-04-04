package com.gleaura.clinic.service.service;

import com.gleaura.clinic.service.dto.request.AppointmentCreateRequest;
import com.gleaura.clinic.service.dto.request.AppointmentUpdateRequest;
import com.gleaura.clinic.service.dto.response.AppointmentResponse;
import com.gleaura.clinic.service.entity.AppointmentEntity;
import com.gleaura.clinic.service.entity.PatientEntity;
import com.gleaura.clinic.service.enums.AppointmentStatus;
import com.gleaura.clinic.service.exception.BusinessException;
import com.gleaura.clinic.service.exception.ErrorCode;
import com.gleaura.clinic.service.mapper.AppointmentMapper;
import com.gleaura.clinic.service.repository.AppointmentRepository;
import com.gleaura.clinic.service.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final AppointmentMapper appointmentMapper;

    @Transactional(readOnly = true)
    public AppointmentResponse getById(Long id) {
        log.info("Randevu getiriliyor. id: {}", id);
        return appointmentMapper.toResponse(findById(id));
    }

    @Transactional(readOnly = true)
    public Page<AppointmentResponse> getAll(Pageable pageable) {
        log.info("Randevular listeleniyor.");
        return appointmentRepository.findAll(pageable).map(appointmentMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<AppointmentResponse> getAllByStatus(AppointmentStatus status, Pageable pageable) {
        log.info("Randevular status'a göre listeleniyor. status: {}", status);
        return appointmentRepository.findAllByStatus(status, pageable).map(appointmentMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<AppointmentResponse> getAllByPatientId(Long patientId, Pageable pageable) {
        log.info("Hastaya ait randevular listeleniyor. patientId: {}", patientId);
        return appointmentRepository.findAllByPatient_Id(patientId, pageable).map(appointmentMapper::toResponse);
    }

    @Transactional
    public AppointmentResponse create(AppointmentCreateRequest request, String createdIp) {
        log.info("Randevu oluşturuluyor. patientId: {}", request.getPatientId());

        PatientEntity patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new BusinessException("Hasta bulunamadı. id: " + request.getPatientId(), ErrorCode.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND));

        AppointmentEntity entity = new AppointmentEntity();
        entity.setPatient(patient);
        entity.setAppointmentDate(request.getAppointmentDate());
        entity.setDurationMinutes(request.getDurationMinutes());
        entity.setDoctor(request.getDoctor());
        entity.setType(request.getType());
        entity.setNote(request.getNote());
        entity.setStatus(AppointmentStatus.SCHEDULED);
        entity.setCreatedBy("SYSTEM");
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCreatedIp(createdIp);

        AppointmentEntity saved = appointmentRepository.save(entity);
        log.info("Randevu oluşturuldu. id: {}", saved.getId());
        return appointmentMapper.toResponse(saved);
    }

    @Transactional
    public AppointmentResponse update(Long id, AppointmentUpdateRequest request, String updatedIp) {
        log.info("Randevu güncelleniyor. id: {}", id);
        AppointmentEntity entity = findById(id);

        entity.setAppointmentDate(request.getAppointmentDate());
        if (request.getDurationMinutes() != null) entity.setDurationMinutes(request.getDurationMinutes());
        if (request.getDoctor() != null) entity.setDoctor(request.getDoctor());
        if (request.getType() != null) entity.setType(request.getType());
        if (request.getNote() != null) entity.setNote(request.getNote());
        entity.setUpdatedBy("SYSTEM");
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setUpdatedIp(updatedIp);

        log.info("Randevu güncellendi. id: {}", id);
        return appointmentMapper.toResponse(appointmentRepository.save(entity));
    }

    @Transactional
    public void changeStatus(Long id, AppointmentStatus status, String updatedIp) {
        log.info("Randevu status değiştiriliyor. id: {}, status: {}", id, status);
        AppointmentEntity entity = findById(id);
        entity.setStatus(status);
        entity.setUpdatedBy("SYSTEM");
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setUpdatedIp(updatedIp);
        appointmentRepository.save(entity);
        log.info("Randevu status değiştirildi. id: {}", id);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Randevu siliniyor. id: {}", id);
        AppointmentEntity entity = findById(id);
        appointmentRepository.delete(entity);
        log.info("Randevu silindi. id: {}", id);
    }

    private AppointmentEntity findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Randevu bulunamadı. id: " + id, ErrorCode.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
