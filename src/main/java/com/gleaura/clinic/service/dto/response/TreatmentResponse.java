package com.gleaura.clinic.service.dto.response;

import com.gleaura.clinic.service.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class TreatmentResponse {

    private Long id;
    private Long appointmentId;
    private String name;
    private String description;
    private LocalDateTime treatmentDate;
    private BigDecimal cost;
    private String currency;
    private String note;
    private Status status;
    private String createdBy;
    private LocalDateTime createdDate;
}
