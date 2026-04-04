package com.gleaura.clinic.service.mapper;

import com.gleaura.clinic.service.dto.response.TreatmentResponse;
import com.gleaura.clinic.service.entity.TreatmentEntity;
import org.springframework.stereotype.Component;

@Component
public class TreatmentMapper {

    public TreatmentResponse toResponse(TreatmentEntity entity) {
        return TreatmentResponse.builder()
                .id(entity.getId())
                .appointmentId(entity.getAppointment().getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .treatmentDate(entity.getTreatmentDate())
                .cost(entity.getCost())
                .currency(entity.getCurrency())
                .note(entity.getNote())
                .status(entity.getStatus())
                .createdBy(entity.getCreatedBy())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
