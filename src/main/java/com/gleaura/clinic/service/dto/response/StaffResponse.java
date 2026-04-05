package com.gleaura.clinic.service.dto.response;

import com.gleaura.clinic.service.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class StaffResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String title;
    private String specialization;
    private String phone;
    private String email;
    private String licenseNumber;
    private LocalDate hireDate;
    private String note;
    private Status status;
    private String createdBy;
    private LocalDateTime createdDate;
}
