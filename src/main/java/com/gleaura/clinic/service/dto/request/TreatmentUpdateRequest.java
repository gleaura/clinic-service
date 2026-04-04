package com.gleaura.clinic.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TreatmentUpdateRequest {

    @NotBlank(message = "Tedavi adı boş olamaz")
    private String name;

    private String description;

    @NotNull(message = "Tedavi tarihi boş olamaz")
    private LocalDateTime treatmentDate;

    private BigDecimal cost;
    private String currency;
    private String note;
}
