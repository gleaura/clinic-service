package com.gleaura.clinic.service.mapper;

import com.gleaura.clinic.service.dto.response.AppointmentResponse;
import com.gleaura.clinic.service.entity.AppointmentEntity;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentResponse toResponse(AppointmentEntity entity) {
        return AppointmentResponse.builder()
                .id(entity.getId())
                .patientId(entity.getPatient().getId())
                .patientFullName(entity.getPatient().getFirstName() + " " + entity.getPatient().getLastName())
                .appointmentDate(entity.getAppointmentDate())
                .durationMinutes(entity.getDurationMinutes())
                .doctor(entity.getDoctor())
                .type(entity.getType())
                .note(entity.getNote())
                .status(entity.getStatus())
                .createdBy(entity.getCreatedBy())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
