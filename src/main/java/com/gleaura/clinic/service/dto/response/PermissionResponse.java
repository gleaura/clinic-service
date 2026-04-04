package com.gleaura.clinic.service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gleaura.clinic.service.enums.PermissionAction;
import com.gleaura.clinic.service.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {

    private Long id;
    private String name;
    private String description;
    private String module;
    private PermissionAction action;
    private Status status;
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime createdDate;
    private String createdBy;
}
