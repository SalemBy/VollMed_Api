package salemby.com.github.medVollApi.domain.appointments.validations;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import salemby.com.github.medVollApi.domain.appointments.AppointmentRepository;
import salemby.com.github.medVollApi.domain.appointments.DataCreateAppointment;
import salemby.com.github.medVollApi.domain.doctors.DoctorRepository;
import salemby.com.github.medVollApi.domain.exceptions.ValidationItemException;

@Component
public class DoctorHasAppointmentValidator implements IValidateCreateAppointment {

    @Autowired
    AppointmentRepository repository;

    public void validate(DataCreateAppointment data) {
        var doctorHasOtherAppointment = repository.existsByIdAndDate(data.idDoctor(), data.date());
        if (doctorHasOtherAppointment) {
            throw new ValidationItemException("Doctor has an appointment");
        }
    }
}
