package com.gleaura.clinic.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientUpdateRequest {

    @NotBlank(message = "Ad boş olamaz")
    private String firstName;

    @NotBlank(message = "Soyad boş olamaz")
    private String lastName;

    private LocalDate birthDate;
    private String phone;
    private String email;
    private String address;
    private String bloodType;
    private String allergies;
    private String chronicDiseases;
    private String note;
}
