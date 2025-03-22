package org.springboot.restapi.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max = 20)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=10, max = 10)
    private String phone;

    private LocalDateTime regDateAndTime;

}
