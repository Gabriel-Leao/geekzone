package com.geekzone.store.dtos;

import com.geekzone.store.entities.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserResponse {
    private final String id;
    private final String email;
    private final String name;
    private final LocalDate birthdate;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.birthdate = user.getBirthdate();
    }
}
