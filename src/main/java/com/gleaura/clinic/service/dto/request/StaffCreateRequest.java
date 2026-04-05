package com.gleaura.clinic.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StaffCreateRequest {

    @NotBlank(message = "Ad boş olamaz")
    private String firstName;

    @NotBlank(message = "Soyad boş olamaz")
    private String lastName;

    private String title;
    private String specialization;
    private String phone;
    private String email;
    private String licenseNumber;
    private LocalDate hireDate;
    private String note;
}
