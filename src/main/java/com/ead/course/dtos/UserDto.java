package com.ead.course.dtos;

import com.ead.course.enums.UserStatus;
import com.ead.course.enums.UserType;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;

    private String userName;

    private String email;

    private String fullName;

    private UserStatus status;

    private UserType type;

    private String phoneNumber;

    private String cpf;

    private String imageUrl;
}
