package salemby.com.github.medVollApi.domain.appointments.validations;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import salemby.com.github.medVollApi.domain.appointments.DataCreateAppointment;
import salemby.com.github.medVollApi.domain.exceptions.ValidationItemException;
import salemby.com.github.medVollApi.domain.patient.PatientRepository;

@Component
public class ActivePatientValidator implements IValidateCreateAppointment {

    @Autowired
    PatientRepository repository;

    public void validate(DataCreateAppointment data) {


        var patientIsActive = repository.findActiveById(data.idPatient());


        if (!patientIsActive) {
            throw new ValidationItemException("Patient is not active");
        }


    }
}
