package com.gleaura.clinic.service.service;

import com.gleaura.clinic.service.dto.request.StaffCreateRequest;
import com.gleaura.clinic.service.dto.request.StaffUpdateRequest;
import com.gleaura.clinic.service.dto.response.StaffResponse;
import com.gleaura.clinic.service.entity.StaffEntity;
import com.gleaura.clinic.service.enums.Status;
import com.gleaura.clinic.service.exception.BusinessException;
import com.gleaura.clinic.service.exception.ErrorCode;
import com.gleaura.clinic.service.mapper.StaffMapper;
import com.gleaura.clinic.service.repository.StaffRepository;
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
public class StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Transactional(readOnly = true)
    public StaffResponse getById(Long id) {
        log.info("Personel getiriliyor. id: {}", id);
        return staffMapper.toResponse(findById(id));
    }

    @Transactional(readOnly = true)
    public Page<StaffResponse> getAll(Pageable pageable) {
        log.info("Personeller listeleniyor.");
        return staffRepository.findAll(pageable).map(staffMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<StaffResponse> getAllByStatus(Status status, Pageable pageable) {
        log.info("Personeller status'a göre listeleniyor. status: {}", status);
        return staffRepository.findAllByStatus(status, pageable).map(staffMapper::toResponse);
    }

    @Transactional
    public StaffResponse create(StaffCreateRequest request, String createdIp) {
        log.info("Personel oluşturuluyor. ad: {} {}", request.getFirstName(), request.getLastName());

        StaffEntity entity = new StaffEntity();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setTitle(request.getTitle());
        entity.setSpecialization(request.getSpecialization());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setLicenseNumber(request.getLicenseNumber());
        entity.setHireDate(request.getHireDate());
        entity.setNote(request.getNote());
        entity.setStatus(Status.ACTIVE);
        entity.setCreatedBy("SYSTEM");
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCreatedIp(createdIp);

        StaffEntity saved = staffRepository.save(entity);
        log.info("Personel oluşturuldu. id: {}", saved.getId());
        return staffMapper.toResponse(saved);
    }

    @Transactional
    public StaffResponse update(Long id, StaffUpdateRequest request, String updatedIp) {
        log.info("Personel güncelleniyor. id: {}", id);
        StaffEntity entity = findById(id);

        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        if (request.getTitle() != null) entity.setTitle(request.getTitle());
        if (request.getSpecialization() != null) entity.setSpecialization(request.getSpecialization());
        if (request.getPhone() != null) entity.setPhone(request.getPhone());
        if (request.getEmail() != null) entity.setEmail(request.getEmail());
        if (request.getLicenseNumber() != null) entity.setLicenseNumber(request.getLicenseNumber());
        if (request.getHireDate() != null) entity.setHireDate(request.getHireDate());
        if (request.getNote() != null) entity.setNote(request.getNote());
        entity.setUpdatedBy("SYSTEM");
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setUpdatedIp(updatedIp);

        log.info("Personel güncellendi. id: {}", id);
        return staffMapper.toResponse(staffRepository.save(entity));
    }

    @Transactional
    public void changeStatus(Long id, Status status, String updatedIp) {
        log.info("Personel status değiştiriliyor. id: {}, status: {}", id, status);
        if (status == Status.BLOCKED) {
            throw new BusinessException("Personel için geçersiz status: " + status, ErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        }
        StaffEntity entity = findById(id);
        entity.setStatus(status);
        entity.setUpdatedBy("SYSTEM");
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setUpdatedIp(updatedIp);
        staffRepository.save(entity);
        log.info("Personel status değiştirildi. id: {}", id);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Personel siliniyor. id: {}", id);
        StaffEntity entity = findById(id);
        staffRepository.delete(entity);
        log.info("Personel silindi. id: {}", id);
    }

    private StaffEntity findById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Personel bulunamadı. id: " + id, ErrorCode.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
