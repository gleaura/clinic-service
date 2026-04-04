package com.gleaura.clinic.service.controller;

import com.gleaura.clinic.service.dto.request.AppointmentCreateRequest;
import com.gleaura.clinic.service.dto.request.AppointmentUpdateRequest;
import com.gleaura.clinic.service.dto.response.AppointmentResponse;
import com.gleaura.clinic.service.enums.AppointmentStatus;
import com.gleaura.clinic.service.service.AppointmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
@Slf4j
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'ASSISTANT', 'RECEPTIONIST')")
    public ResponseEntity<AppointmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'ASSISTANT', 'RECEPTIONIST')")
    public ResponseEntity<Page<AppointmentResponse>> getAll(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getAll(pageable));
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'ASSISTANT', 'RECEPTIONIST')")
    public ResponseEntity<Page<AppointmentResponse>> getAllByStatus(
            @PathVariable AppointmentStatus status,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getAllByStatus(status, pageable));
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'ASSISTANT', 'RECEPTIONIST')")
    public ResponseEntity<Page<AppointmentResponse>> getAllByPatientId(
            @PathVariable Long patientId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getAllByPatientId(patientId, pageable));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'ASSISTANT', 'RECEPTIONIST')")
    public ResponseEntity<AppointmentResponse> create(
            @Valid @RequestBody AppointmentCreateRequest request,
            HttpServletRequest httpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.create(request, httpRequest.getRemoteAddr()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'ASSISTANT', 'RECEPTIONIST')")
    public ResponseEntity<AppointmentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentUpdateRequest request,
            HttpServletRequest httpRequest) {
        return ResponseEntity.ok(appointmentService.update(id, request, httpRequest.getRemoteAddr()));
    }

    @PatchMapping("/{id}/status/{status}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'ASSISTANT', 'RECEPTIONIST')")
    public ResponseEntity<Void> changeStatus(
            @PathVariable Long id,
            @PathVariable AppointmentStatus status,
            HttpServletRequest httpRequest) {
        appointmentService.changeStatus(id, status, httpRequest.getRemoteAddr());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
