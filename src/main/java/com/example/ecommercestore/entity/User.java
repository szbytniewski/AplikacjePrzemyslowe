package com.example.ecommercestore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true, nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 100, message = "Password should be between 6 and 50 characters")
    private String password;

    // nie potrzeba walidacji bo uzytkownik tego nie jest w stanie podac
    private String role; // "USER", "ADMIN"
}