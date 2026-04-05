package com.gleaura.clinic.service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentCreateRequest {

    @NotNull(message = "Hasta ID boş olamaz")
    private Long patientId;

    @NotNull(message = "Randevu tarihi boş olamaz")
    private LocalDateTime appointmentDate;

    private Integer durationMinutes;
    private Long staffId;
    private String type;
    private String note;
}
