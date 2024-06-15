package salemby.com.github.medVollApi.domain.appointments.validations;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import salemby.com.github.medVollApi.domain.appointments.DataCreateAppointment;
import salemby.com.github.medVollApi.domain.doctors.DoctorRepository;
import salemby.com.github.medVollApi.domain.exceptions.ValidationItemException;

@Component
public class ActiveDoctorValidator implements IValidateCreateAppointment {

    @Autowired
    private DoctorRepository repository;

    public void validate(DataCreateAppointment data) {
        if (data.idDoctor() == null) {
            return;
        } else {
            var doctorIsActive = repository.findActiveById(data.idDoctor());
            if (!doctorIsActive) {
                throw new ValidationItemException("Doctor is not active");
            }
        }

    }
}
