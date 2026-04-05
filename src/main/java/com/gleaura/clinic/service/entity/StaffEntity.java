package com.gleaura.clinic.service.entity;

import com.gleaura.clinic.service.entity.base.BaseEntity;
import com.gleaura.clinic.service.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "STAFF", schema = "clinic")
@Getter
@Setter
public class StaffEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STAFF")
    @SequenceGenerator(name = "SEQ_STAFF", sequenceName = "clinic.SEQ_STAFF", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    @Column(name = "TITLE", length = 100)
    private String title;

    @Column(name = "SPECIALIZATION", length = 200)
    private String specialization;

    @Column(name = "PHONE", length = 20)
    private String phone;

    @Column(name = "EMAIL", length = 150)
    private String email;

    @Column(name = "LICENSE_NUMBER", length = 50)
    private String licenseNumber;

    @Column(name = "HIRE_DATE")
    private LocalDate hireDate;

    @Column(name = "NOTE", columnDefinition = "TEXT")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private Status status = Status.ACTIVE;
}
