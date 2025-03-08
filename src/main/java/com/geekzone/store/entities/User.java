package com.geekzone.store.entities;

import com.geekzone.store.dtos.UserRequest;
import com.geekzone.store.models.Role;
import com.geekzone.store.utils.DateFormatterUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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

    public User (UserRequest userRequestData) {
        this.email = userRequestData.getEmail();
        this.name = userRequestData.getName();
        this.password = userRequestData.getPassword();
        this.birthdate = transformStringToLocalDate(userRequestData.getBirthdate());
        this.role = transformStringToRole(userRequestData.getRole());
    }

    private LocalDate transformStringToLocalDate(String date) {
        return LocalDate.parse(date, DateFormatterUtil.dateFormatter);
    }

    private Role transformStringToRole(String role) {
        return role == null || role.isEmpty()
                ? Role.USER
                : Role.valueOf(role.toUpperCase());
    }

    public void setRole(String role) {
        this.role = transformStringToRole(role);
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = transformStringToLocalDate(birthdate);
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
