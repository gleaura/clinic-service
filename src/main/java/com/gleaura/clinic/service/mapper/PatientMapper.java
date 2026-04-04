package com.gleaura.clinic.service.mapper;

import com.gleaura.clinic.service.dto.request.PatientCreateRequest;
import com.gleaura.clinic.service.dto.response.PatientResponse;
import com.gleaura.clinic.service.entity.PatientEntity;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public PatientResponse toResponse(PatientEntity entity) {
        return PatientResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .identityNumber(entity.getIdentityNumber())
                .bloodType(entity.getBloodType())
                .allergies(entity.getAllergies())
                .chronicDiseases(entity.getChronicDiseases())
                .note(entity.getNote())
                .status(entity.getStatus())
                .createdBy(entity.getCreatedBy())
                .createdDate(entity.getCreatedDate())
                .build();
    }

    public PatientEntity toEntity(PatientCreateRequest request) {
        PatientEntity entity = new PatientEntity();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setBirthDate(request.getBirthDate());
        entity.setGender(request.getGender());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setAddress(request.getAddress());
        entity.setIdentityNumber(request.getIdentityNumber());
        entity.setBloodType(request.getBloodType());
        entity.setAllergies(request.getAllergies());
        entity.setChronicDiseases(request.getChronicDiseases());
        entity.setNote(request.getNote());
        return entity;
    }
}
