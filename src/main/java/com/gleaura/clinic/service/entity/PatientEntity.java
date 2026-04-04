package com.gleaura.clinic.service.entity;

import com.gleaura.clinic.service.entity.base.BaseEntity;
import com.gleaura.clinic.service.enums.Gender;
import com.gleaura.clinic.service.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "PATIENT", schema = "clinic")
@Getter
@Setter
public class PatientEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PATIENT")
    @SequenceGenerator(name = "SEQ_PATIENT", sequenceName = "clinic.SEQ_PATIENT", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 10)
    private Gender gender;

    @Column(name = "PHONE", length = 20)
    private String phone;

    @Column(name = "EMAIL", length = 150)
    private String email;

    @Column(name = "ADDRESS", length = 500)
    private String address;

    @Column(name = "IDENTITY_NUMBER", length = 20)
    private String identityNumber;

    @Column(name = "BLOOD_TYPE", length = 10)
    private String bloodType;

    @Column(name = "ALLERGIES", columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "CHRONIC_DISEASES", columnDefinition = "TEXT")
    private String chronicDiseases;

    @Column(name = "NOTE", columnDefinition = "TEXT")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private Status status = Status.ACTIVE;
}
