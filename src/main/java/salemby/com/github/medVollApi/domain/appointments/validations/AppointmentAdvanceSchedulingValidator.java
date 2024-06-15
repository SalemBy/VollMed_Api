package salemby.com.github.medVollApi.domain.appointments.validations;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import salemby.com.github.medVollApi.domain.appointments.DataCreateAppointment;
import salemby.com.github.medVollApi.domain.exceptions.ValidationItemException;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AppointmentAdvanceSchedulingValidator implements IValidateCreateAppointment {

    public void validate(DataCreateAppointment data) {
        var dateAppointment = data.date();
        var now = LocalDateTime.now();
        var minutesBefore = Duration.between(now, dateAppointment).toMinutes();

        if (minutesBefore < 30) {
            throw new ValidationItemException("Appoint must be scheduled at least 30 minutes before");
        }

    }
}
