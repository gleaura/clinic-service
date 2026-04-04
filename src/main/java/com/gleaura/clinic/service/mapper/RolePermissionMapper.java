package com.gleaura.clinic.service.mapper;

import com.gleaura.clinic.service.dto.response.RolePermissionResponse;
import com.gleaura.clinic.service.entity.RolePermissionEntity;
import org.springframework.stereotype.Component;

@Component
public class RolePermissionMapper {

    public RolePermissionResponse toResponse(RolePermissionEntity entity) {
        return RolePermissionResponse.builder()
                .id(entity.getId())
                .roleId(entity.getRole().getId())
                .roleName(entity.getRole().getName())
                .permissionId(entity.getPermission().getId())
                .permissionName(entity.getPermission().getName())
                .module(entity.getPermission().getModule())
                .action(entity.getPermission().getAction())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
