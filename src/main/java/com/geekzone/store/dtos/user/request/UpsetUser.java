package com.geekzone.store.dtos.user.request;

import com.geekzone.store.models.Roles;
import com.geekzone.store.validations.birthdate.ValidBirthdate;
import com.geekzone.store.validations.enums.ValidateEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpsetUser {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email should be valid")
    @Size(min = 5, max = 100, message = "Email must be between 5 and 100 characters")
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password can't be empty")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    @Setter
    private String password;

    @ValidBirthdate
    private String birthdate;

    @ValidateEnum(message = "Invalid role. Allowed values are USER and ADMIN.", enumClass = Roles.class)
    private String role;

    @NotNull(message = "Address is required")
    private UpsetAddress address;
}
