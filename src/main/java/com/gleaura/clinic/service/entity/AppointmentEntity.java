package com.gleaura.clinic.service.entity;

import com.gleaura.clinic.service.entity.base.BaseEntity;
import com.gleaura.clinic.service.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "APPOINTMENT", schema = "clinic")
@Getter
@Setter
public class AppointmentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPOINTMENT")
    @SequenceGenerator(name = "SEQ_APPOINTMENT", sequenceName = "clinic.SEQ_APPOINTMENT", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    private PatientEntity patient;

    @Column(name = "APPOINTMENT_DATE", nullable = false)
    private LocalDateTime appointmentDate;

    @Column(name = "DURATION_MINUTES")
    private Integer durationMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAFF_ID")
    private StaffEntity staff;

    @Column(name = "TYPE", length = 100)
    private String type;

    @Column(name = "NOTE", columnDefinition = "TEXT")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;
}
