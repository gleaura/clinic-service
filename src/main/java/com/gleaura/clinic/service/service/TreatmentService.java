package com.gleaura.clinic.service.service;

import com.gleaura.clinic.service.dto.request.TreatmentCreateRequest;
import com.gleaura.clinic.service.dto.request.TreatmentUpdateRequest;
import com.gleaura.clinic.service.dto.response.TreatmentResponse;
import com.gleaura.clinic.service.entity.AppointmentEntity;
import com.gleaura.clinic.service.entity.TreatmentEntity;
import com.gleaura.clinic.service.enums.Status;
import com.gleaura.clinic.service.exception.BusinessException;
import com.gleaura.clinic.service.exception.ErrorCode;
import com.gleaura.clinic.service.mapper.TreatmentMapper;
import com.gleaura.clinic.service.repository.AppointmentRepository;
import com.gleaura.clinic.service.repository.TreatmentRepository;
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
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final AppointmentRepository appointmentRepository;
    private final TreatmentMapper treatmentMapper;

    @Transactional(readOnly = true)
    public TreatmentResponse getById(Long id) {
        log.info("Tedavi getiriliyor. id: {}", id);
        return treatmentMapper.toResponse(findById(id));
    }

    @Transactional(readOnly = true)
    public Page<TreatmentResponse> getAll(Pageable pageable) {
        log.info("Tedaviler listeleniyor.");
        return treatmentRepository.findAll(pageable).map(treatmentMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<TreatmentResponse> getAllByStatus(Status status, Pageable pageable) {
        log.info("Tedaviler status'a göre listeleniyor. status: {}", status);
        return treatmentRepository.findAllByStatus(status, pageable).map(treatmentMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<TreatmentResponse> getAllByAppointmentId(Long appointmentId, Pageable pageable) {
        log.info("Randevuya ait tedaviler listeleniyor. appointmentId: {}", appointmentId);
        return treatmentRepository.findAllByAppointment_Id(appointmentId, pageable).map(treatmentMapper::toResponse);
    }

    @Transactional
    public TreatmentResponse create(TreatmentCreateRequest request, String createdIp) {
        log.info("Tedavi oluşturuluyor. appointmentId: {}", request.getAppointmentId());

        AppointmentEntity appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new BusinessException("Randevu bulunamadı. id: " + request.getAppointmentId(), ErrorCode.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND));

        TreatmentEntity entity = new TreatmentEntity();
        entity.setAppointment(appointment);
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setTreatmentDate(request.getTreatmentDate());
        entity.setCost(request.getCost());
        entity.setCurrency(request.getCurrency());
        entity.setNote(request.getNote());
        entity.setStatus(Status.ACTIVE);
        entity.setCreatedBy("SYSTEM");
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCreatedIp(createdIp);

        TreatmentEntity saved = treatmentRepository.save(entity);
        log.info("Tedavi oluşturuldu. id: {}", saved.getId());
        return treatmentMapper.toResponse(saved);
    }

    @Transactional
    public TreatmentResponse update(Long id, TreatmentUpdateRequest request, String updatedIp) {
        log.info("Tedavi güncelleniyor. id: {}", id);
        TreatmentEntity entity = findById(id);

        entity.setName(request.getName());
        entity.setTreatmentDate(request.getTreatmentDate());
        if (request.getDescription() != null) entity.setDescription(request.getDescription());
        if (request.getCost() != null) entity.setCost(request.getCost());
        if (request.getCurrency() != null) entity.setCurrency(request.getCurrency());
        if (request.getNote() != null) entity.setNote(request.getNote());
        entity.setUpdatedBy("SYSTEM");
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setUpdatedIp(updatedIp);

        log.info("Tedavi güncellendi. id: {}", id);
        return treatmentMapper.toResponse(treatmentRepository.save(entity));
    }

    @Transactional
    public void changeStatus(Long id, Status status, String updatedIp) {
        log.info("Tedavi status değiştiriliyor. id: {}, status: {}", id, status);
        if (status == Status.BLOCKED) {
            throw new BusinessException("Tedavi için geçersiz status: " + status, ErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        }
        TreatmentEntity entity = findById(id);
        entity.setStatus(status);
        entity.setUpdatedBy("SYSTEM");
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setUpdatedIp(updatedIp);
        treatmentRepository.save(entity);
        log.info("Tedavi status değiştirildi. id: {}", id);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Tedavi siliniyor. id: {}", id);
        TreatmentEntity entity = findById(id);
        treatmentRepository.delete(entity);
        log.info("Tedavi silindi. id: {}", id);
    }

    private TreatmentEntity findById(Long id) {
        return treatmentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Tedavi bulunamadı. id: " + id, ErrorCode.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
