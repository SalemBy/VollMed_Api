package salemby.com.github.medVollApi.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Address {

    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String zip;
    private String complement;
    private String number;

    public Address(AddressData address) {
        this.street = address.street();
        this.neighborhood = address.neighborhood();
        this.city = address.city();
        this.state = address.state();
        this.zip = address.zip();
        this.complement = address.complement();
        this.number = address.number();
    }

    public void updateAddress(AddressData data) {
        if (data.street() != null) {
            this.street = data.street();
        }

        if (data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }

        if (data.city() != null) {
            this.city = data.city();
        }

        if (data.state() != null) {
            this.state = data.state();
        }

        if (data.zip() != null) {
            this.zip = data.zip();
        }

        if (data.complement() != null) {
            this.complement = data.complement();
        }

        if (data.number() != null) {
            this.number = data.number();
        }
    }
}
