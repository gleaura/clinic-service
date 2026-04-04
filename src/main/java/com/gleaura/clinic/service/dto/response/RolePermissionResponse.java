package com.gleaura.clinic.service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gleaura.clinic.service.enums.PermissionAction;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionResponse {

    private Long id;
    private Long roleId;
    private String roleName;
    private Long permissionId;
    private String permissionName;
    private String module;
    private PermissionAction action;
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime createdDate;
}
