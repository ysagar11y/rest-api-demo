package org.springboot.restapi.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    @NotBlank(message = "Cannot be Empty")
    private String username;

    @Email(message = "Invalid email ID")
    @NotBlank(message = "email cannot be empty")
    private String email;

    @NotBlank(message = "Phone number is required!")
    @Size(min = 10, max = 10, message = "Phone number must have 10 characters!")
    @Pattern(regexp="^[0-9]*$", message = "Phone number must contain only digits")
    private String phone;
}
