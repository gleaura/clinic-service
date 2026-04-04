package com.gleaura.clinic.service.dto.response;

import com.gleaura.clinic.service.enums.AppointmentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AppointmentResponse {

    private Long id;
    private Long patientId;
    private String patientFullName;
    private LocalDateTime appointmentDate;
    private Integer durationMinutes;
    private String doctor;
    private String type;
    private String note;
    private AppointmentStatus status;
    private String createdBy;
    private LocalDateTime createdDate;
}
