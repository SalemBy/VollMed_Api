package salemby.com.github.medVollApi.domain.doctors;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salemby.com.github.medVollApi.domain.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;

    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Embedded
    private Address address;

    public Doctor(DoctorRegistrationData data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.crm = data.crm();
        this.speciality = data.speciality();
        this.address = new Address(data.address());
        this.phone = data.phone();
    }

    public void updateData(DoctorUpdateDTO data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.phone() != null) {
            this.phone = data.phone();
        }

        if(data.addressData() != null) {
            this.address.updateAddress(data.addressData());
        }
    }

    public void logicalDelete() {
        this.active = false;
    }
}
