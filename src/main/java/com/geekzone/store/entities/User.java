package com.geekzone.store.entities;

import com.geekzone.store.dtos.UserRequest;
import com.geekzone.store.models.Role;
import com.geekzone.store.utils.DateFormatterUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public User (UserRequest userRequestData, String password) {
        this.email = userRequestData.getEmail();
        this.name = userRequestData.getName();
        this.password = password;
        this.birthdate = LocalDate.parse(userRequestData.getBirthdate(), DateFormatterUtil.dateFormatter);
        this.role = (userRequestData.getRole() == null || userRequestData.getRole().isEmpty())
                ? Role.USER
                : Role.valueOf(userRequestData.getRole().toUpperCase());
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
