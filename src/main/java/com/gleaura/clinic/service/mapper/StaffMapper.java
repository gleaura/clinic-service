package com.gleaura.clinic.service.mapper;

import com.gleaura.clinic.service.dto.response.StaffResponse;
import com.gleaura.clinic.service.entity.StaffEntity;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper {

    public StaffResponse toResponse(StaffEntity entity) {
        return StaffResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .title(entity.getTitle())
                .specialization(entity.getSpecialization())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .licenseNumber(entity.getLicenseNumber())
                .hireDate(entity.getHireDate())
                .note(entity.getNote())
                .status(entity.getStatus())
                .createdBy(entity.getCreatedBy())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
