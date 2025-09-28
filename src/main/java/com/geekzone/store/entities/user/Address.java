package com.geekzone.store.entities.user;

import com.geekzone.store.dtos.user.request.UpsetAddress;
import com.geekzone.store.models.BrazilianStates;
import com.geekzone.store.utils.EnumConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor()
public class Address {
    @Column(nullable = false)
    private String city;

    @Column()
    private String complement;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false, name = "postal_code")
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private BrazilianStates state;

    @Column(nullable = false)
    private String street;

    public Address(UpsetAddress address) {
        this.city = address.getCity();
        this.complement = address.getComplement();
        this.neighborhood = address.getNeighborhood();
        this.number = address.getNumber();
        this.postalCode = address.getPostalCode();
        setState(address.getState());
        this.street = address.getStreet();
    }

    public BrazilianStates transformStringToBrazilianState(String state) {
        return EnumConverter.toEnum(state, BrazilianStates.class);
    }

    public void setState(String state) {
        this.state = transformStringToBrazilianState(state);
    }
}
