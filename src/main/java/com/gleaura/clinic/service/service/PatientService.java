package com.gleaura.clinic.service.service;

import com.gleaura.clinic.service.dto.request.PatientCreateRequest;
import com.gleaura.clinic.service.dto.request.PatientUpdateRequest;
import com.gleaura.clinic.service.dto.response.PatientResponse;
import com.gleaura.clinic.service.entity.PatientEntity;
import com.gleaura.clinic.service.enums.Status;
import com.gleaura.clinic.service.exception.BusinessException;
import com.gleaura.clinic.service.exception.ErrorCode;
import com.gleaura.clinic.service.mapper.PatientMapper;
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
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Transactional(readOnly = true)
    public PatientResponse getById(Long id) {
        log.info("Hasta getiriliyor. id: {}", id);
        return patientMapper.toResponse(findById(id));
    }

    @Transactional(readOnly = true)
    public Page<PatientResponse> getAll(Pageable pageable) {
        log.info("Hastalar listeleniyor.");
        return patientRepository.findAll(pageable).map(patientMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<PatientResponse> getAllByStatus(Status status, Pageable pageable) {
        log.info("Hastalar status'a göre listeleniyor. status: {}", status);
        return patientRepository.findAllByStatus(status, pageable).map(patientMapper::toResponse);
    }

    @Transactional
    public PatientResponse create(PatientCreateRequest request, String createdIp) {
        log.info("Hasta oluşturuluyor. ad: {} {}", request.getFirstName(), request.getLastName());

        if (request.getIdentityNumber() != null && patientRepository.existsByIdentityNumber(request.getIdentityNumber())) {
            throw new BusinessException("Bu TC kimlik numarası zaten kayıtlı", ErrorCode.DUPLICATE_ENTRY, HttpStatus.CONFLICT);
        }

        PatientEntity entity = patientMapper.toEntity(request);
        entity.setStatus(Status.ACTIVE);
        entity.setCreatedBy("SYSTEM");
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCreatedIp(createdIp);

        PatientEntity saved = patientRepository.save(entity);
        log.info("Hasta oluşturuldu. id: {}", saved.getId());
        return patientMapper.toResponse(saved);
    }

    @Transactional
    public PatientResponse update(Long id, PatientUpdateRequest request, String updatedIp) {
        log.info("Hasta güncelleniyor. id: {}", id);
        PatientEntity entity = findById(id);

        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        if (request.getBirthDate() != null) entity.setBirthDate(request.getBirthDate());
        if (request.getPhone() != null) entity.setPhone(request.getPhone());
        if (request.getEmail() != null) entity.setEmail(request.getEmail());
        if (request.getAddress() != null) entity.setAddress(request.getAddress());
        if (request.getBloodType() != null) entity.setBloodType(request.getBloodType());
        if (request.getAllergies() != null) entity.setAllergies(request.getAllergies());
        if (request.getChronicDiseases() != null) entity.setChronicDiseases(request.getChronicDiseases());
        if (request.getNote() != null) entity.setNote(request.getNote());
        entity.setUpdatedBy("SYSTEM");
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setUpdatedIp(updatedIp);

        log.info("Hasta güncellendi. id: {}", id);
        return patientMapper.toResponse(patientRepository.save(entity));
    }

    @Transactional
    public void changeStatus(Long id, Status status, String updatedIp) {
        log.info("Hasta status değiştiriliyor. id: {}, status: {}", id, status);
        if (status == Status.BLOCKED) {
            throw new BusinessException("Hasta için geçersiz status: " + status, ErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        }
        PatientEntity entity = findById(id);
        entity.setStatus(status);
        entity.setUpdatedBy("SYSTEM");
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setUpdatedIp(updatedIp);
        patientRepository.save(entity);
        log.info("Hasta status değiştirildi. id: {}", id);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Hasta siliniyor. id: {}", id);
        PatientEntity entity = findById(id);
        patientRepository.delete(entity);
        log.info("Hasta silindi. id: {}", id);
    }

    private PatientEntity findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Hasta bulunamadı. id: " + id, ErrorCode.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
