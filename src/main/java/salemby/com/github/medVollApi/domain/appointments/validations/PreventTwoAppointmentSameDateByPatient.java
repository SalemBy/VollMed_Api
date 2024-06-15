package salemby.com.github.medVollApi.domain.appointments.validations;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import salemby.com.github.medVollApi.domain.appointments.AppointmentRepository;
import salemby.com.github.medVollApi.domain.appointments.DataCreateAppointment;
import salemby.com.github.medVollApi.domain.exceptions.ValidationItemException;
import salemby.com.github.medVollApi.domain.patient.PatientRepository;

@Component
public class PreventTwoAppointmentSameDateByPatient implements IValidateCreateAppointment {

    @Autowired
    private AppointmentRepository repository;

    public void validate(DataCreateAppointment data) {
        var firstHour = data.date().withHour(7);
        var lastHour = data.date().withHour(18);

        var patientHaveOtherAppointmentInDate = repository.existsByPatientIdAndDateBetween(data.idPatient(), firstHour, lastHour);

        if (patientHaveOtherAppointmentInDate) {
            throw new ValidationItemException("Patient already has an appointment in this day");
        }
    }
}
