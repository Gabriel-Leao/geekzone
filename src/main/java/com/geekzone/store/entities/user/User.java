package com.geekzone.store.entities.user;

import com.geekzone.store.dtos.user.request.UpsetUser;
import com.geekzone.store.models.Roles;
import com.geekzone.store.utils.DateFormatterUtil;
import com.geekzone.store.utils.EnumConverter;
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
    private Roles role;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Embedded
    private Address address;

    public User (UpsetUser userRequestData) {
        this.email = userRequestData.getEmail();
        this.name = userRequestData.getName();
        this.password = userRequestData.getPassword();
        setRole(userRequestData.getRole());
        setBirthdate(userRequestData.getBirthdate());
        this.address = new Address(userRequestData.getAddress());
    }

    private Roles transformStringToRole(String role) {
        return EnumConverter.toEnumOrDefault(role, Roles.class, Roles.USER);
    }

    public void setRole(String role) {
        this.role = transformStringToRole(role);
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = DateFormatterUtil.transformStringToLocalDate(birthdate);
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
