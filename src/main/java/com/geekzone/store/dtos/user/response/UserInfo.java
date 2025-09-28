package com.geekzone.store.dtos.user.response;

import com.geekzone.store.entities.user.Address;
import com.geekzone.store.entities.user.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserInfo {
    private final long id;
    private final String email;
    private final String name;
    private final LocalDate birthdate;
    private final Address address;

    public UserInfo(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.birthdate = user.getBirthdate();
        this.address = user.getAddress();
    }
}
