package com.gleaura.clinic.service.dto.response;

import com.gleaura.clinic.service.enums.Gender;
import com.gleaura.clinic.service.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class PatientResponse {

    private Long id;
    private String firstName;
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
    private Status status;
    private String createdBy;
    private LocalDateTime createdDate;
}
