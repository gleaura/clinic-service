package com.gleaura.clinic.service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gleaura.clinic.service.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Status status;
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime lastLoginDate;
    private Integer errorLoginCount;
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime createdDate;
    private String createdBy;
}
