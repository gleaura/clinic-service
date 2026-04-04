package com.gleaura.clinic.service.controller;

import com.gleaura.clinic.service.dto.request.TreatmentCreateRequest;
import com.gleaura.clinic.service.dto.request.TreatmentUpdateRequest;
import com.gleaura.clinic.service.dto.response.TreatmentResponse;
import com.gleaura.clinic.service.enums.Status;
import com.gleaura.clinic.service.service.TreatmentService;
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
@RequestMapping("/api/v1/treatments")
@RequiredArgsConstructor
@Slf4j
public class TreatmentController {

    private final TreatmentService treatmentService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'ASSISTANT')")
    public ResponseEntity<TreatmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(treatmentService.getById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'ASSISTANT')")
    public ResponseEntity<Page<TreatmentResponse>> getAll(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(treatmentService.getAll(pageable));
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'ASSISTANT')")
    public ResponseEntity<Page<TreatmentResponse>> getAllByStatus(
            @PathVariable Status status,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(treatmentService.getAllByStatus(status, pageable));
    }

    @GetMapping("/appointment/{appointmentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'ASSISTANT')")
    public ResponseEntity<Page<TreatmentResponse>> getAllByAppointmentId(
            @PathVariable Long appointmentId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(treatmentService.getAllByAppointmentId(appointmentId, pageable));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<TreatmentResponse> create(
            @Valid @RequestBody TreatmentCreateRequest request,
            HttpServletRequest httpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(treatmentService.create(request, httpRequest.getRemoteAddr()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<TreatmentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TreatmentUpdateRequest request,
            HttpServletRequest httpRequest) {
        return ResponseEntity.ok(treatmentService.update(id, request, httpRequest.getRemoteAddr()));
    }

    @PatchMapping("/{id}/status/{status}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<Void> changeStatus(
            @PathVariable Long id,
            @PathVariable Status status,
            HttpServletRequest httpRequest) {
        treatmentService.changeStatus(id, status, httpRequest.getRemoteAddr());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        treatmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
