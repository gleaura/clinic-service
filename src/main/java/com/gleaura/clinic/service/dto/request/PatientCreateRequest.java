package com.gleaura.clinic.service.dto.request;

import com.gleaura.clinic.service.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientCreateRequest {

    @NotBlank(message = "Ad boş olamaz")
    private String firstName;

    @NotBlank(message = "Soyad boş olamaz")
    private String lastName;

    private LocalDate birthDate;
    private Gender gender;
    private String phone;
    private String email;
    private String address;
    private String identityNumber;
    private String bloodType;
    private String allergies;
    private String chronicDiseases;
    private String note;
}
