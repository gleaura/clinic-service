package com.gleaura.clinic.service.repository;

import com.gleaura.clinic.service.entity.PermissionEntity;
import com.gleaura.clinic.service.enums.PermissionAction;
import com.gleaura.clinic.service.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    Optional<PermissionEntity> findByName(String name);

    boolean existsByName(String name);

    boolean existsByModuleAndAction(String module, PermissionAction action);

    List<PermissionEntity> findAllByModule(String module);

    Page<PermissionEntity> findAllByStatus(Status status, Pageable pageable);
}
