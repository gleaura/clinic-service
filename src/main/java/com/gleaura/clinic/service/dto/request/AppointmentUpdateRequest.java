package com.gleaura.clinic.service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentUpdateRequest {

    @NotNull(message = "Randevu tarihi boş olamaz")
    private LocalDateTime appointmentDate;

    private Integer durationMinutes;
    private Long staffId;
    private String type;
    private String note;
}
