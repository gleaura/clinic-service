package com.gleaura.clinic.service.entity;

import com.gleaura.clinic.service.entity.base.BaseEntity;
import com.gleaura.clinic.service.enums.PermissionAction;
import com.gleaura.clinic.service.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PERMISSION", schema = "clinic")
@Getter
@Setter
public class PermissionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_seq")
    @SequenceGenerator(name = "permission_seq", sequenceName = "clinic.SEQ_PERMISSION", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "MODULE", nullable = false, length = 100)
    private String module;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTION", nullable = false, length = 50)
    private PermissionAction action;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private Status status = Status.ACTIVE;
}
