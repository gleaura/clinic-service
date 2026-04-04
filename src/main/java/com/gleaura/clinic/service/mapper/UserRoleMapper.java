package com.gleaura.clinic.service.mapper;

import com.gleaura.clinic.service.dto.response.UserRoleResponse;
import com.gleaura.clinic.service.entity.UserRoleEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

    public UserRoleResponse toResponse(UserRoleEntity entity) {
        return UserRoleResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .username(entity.getUser().getUsername())
                .roleId(entity.getRole().getId())
                .roleName(entity.getRole().getName())
                .assignedBy(entity.getAssignedBy())
                .assignedDate(entity.getAssignedDate())
                .build();
    }
}
