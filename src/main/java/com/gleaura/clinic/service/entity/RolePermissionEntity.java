package com.gleaura.clinic.service.entity;

import com.gleaura.clinic.service.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLE_PERMISSION", schema = "clinic")
@Getter
@Setter
public class RolePermissionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_permission_seq")
    @SequenceGenerator(name = "role_permission_seq", sequenceName = "clinic.SEQ_ROLE_PERMISSION", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ROLE_PERMISSION_ROLE"))
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERMISSION_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ROLE_PERMISSION_PERMISSION"))
    private PermissionEntity permission;
}
