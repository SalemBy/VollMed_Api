package salemby.com.github.medVollApi.domain.appointments;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import salemby.com.github.medVollApi.domain.doctors.Speciality;

import java.time.LocalDateTime;

public record DataCreateAppointment(
        Long idDoctor,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime date,

        Speciality speciality
) {
}
