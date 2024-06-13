package salemby.com.github.medVollApi.doctors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import salemby.com.github.medVollApi.address.AddressData;

public record DoctorUpdateDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressData addressData) {

}
