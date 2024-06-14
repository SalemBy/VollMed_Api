package salemby.com.github.medVollApi.domain.doctors;

import jakarta.validation.constraints.NotNull;
import salemby.com.github.medVollApi.domain.address.AddressData;

public record DoctorUpdateDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressData addressData) {

}
