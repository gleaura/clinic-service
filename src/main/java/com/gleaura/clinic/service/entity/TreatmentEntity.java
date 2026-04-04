package com.gleaura.clinic.service.entity;

import com.gleaura.clinic.service.entity.base.BaseEntity;
import com.gleaura.clinic.service.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TREATMENT", schema = "clinic")
@Getter
@Setter
public class TreatmentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TREATMENT")
    @SequenceGenerator(name = "SEQ_TREATMENT", sequenceName = "clinic.SEQ_TREATMENT", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPOINTMENT_ID", nullable = false)
    private AppointmentEntity appointment;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "TREATMENT_DATE", nullable = false)
    private LocalDateTime treatmentDate;

    @Column(name = "COST", precision = 12, scale = 2)
    private BigDecimal cost;

    @Column(name = "CURRENCY", length = 10)
    private String currency;

    @Column(name = "NOTE", columnDefinition = "TEXT")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private Status status = Status.ACTIVE;
}
