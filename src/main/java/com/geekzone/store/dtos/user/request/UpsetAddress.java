package com.geekzone.store.dtos.user.request;

import com.geekzone.store.models.BrazilianStates;
import com.geekzone.store.validations.enums.ValidateEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpsetAddress {
    @NotNull(message = "City is required")
    @NotBlank(message = "City can't be empty")
    @Size(max = 255, message = "city name should have at least 1 character and 255 characters at most.")
    private String city;

    @NotBlank(message = "Complement can't be empty")
    @Size(min = 3, max = 255, message = "Complement name should have at least 3 characters and 255 characters at most.")
    private String complement;

    @NotNull(message = "Neighborhood is required")
    @NotBlank(message = "Neighborhood can't be empty")
    @Size(max = 255, message = "Neighborhood name should have at least 1 character and 255 characters at most.")
    private String neighborhood;

    @NotNull(message = "Number is required")
    @NotBlank(message = "Number can't be empty")
    @Size(max = 10, message = "Number should have at least 1 character and 10 characters at most.")
    private String number;

    @NotNull(message = "State is required")
    @NotBlank(message = "State can't be empty")
    @ValidateEnum(message = "State must be a Brazilian UF", enumClass = BrazilianStates.class)
    private String state;

    @NotNull(message = "PostalCode is required")
    @NotBlank(message = "PostalCode can't be empty")
    @Size(min = 8, max = 8, message = "postalCode should have exactly 8 numbers.")
    private String postalCode;

    @NotNull(message = "Street is required")
    @NotBlank(message = "Street can't be empty")
    @Size(min = 5, max = 255, message = "Street name should have at least 5 characters and 255 characters at most.")
    private String street;
}
