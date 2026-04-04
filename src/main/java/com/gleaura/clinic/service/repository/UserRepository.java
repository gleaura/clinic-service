package com.gleaura.clinic.service.repository;

import com.gleaura.clinic.service.entity.UserEntity;
import com.gleaura.clinic.service.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Page<UserEntity> findAllByStatus(Status status, Pageable pageable);
}
