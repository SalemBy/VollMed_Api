package salemby.com.github.medVollApi.domain.patient;

import jakarta.validation.constraints.NotNull;
import salemby.com.github.medVollApi.domain.address.AddressData;

public record DataUpdatePatient(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressData address
) {
}
