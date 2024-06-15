package salemby.com.github.medVollApi.domain.appointments.validations;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import salemby.com.github.medVollApi.domain.appointments.DataCreateAppointment;
import salemby.com.github.medVollApi.domain.exceptions.ValidationItemException;

import java.time.DayOfWeek;

@Component
public class OperatingHoursValidator implements IValidateCreateAppointment {
    public void validate(DataCreateAppointment data) {
        var dateAppointment = data.date();

        var sunday = dateAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpening = dateAppointment.getHour() < 7;
        var beforeClosed = dateAppointment.getHour() >= 18;

        if (sunday || beforeOpening || beforeClosed) {
            throw new ValidationItemException("Date now allowed, must be between 7 and 18 in working days.");
        }
    }
}
