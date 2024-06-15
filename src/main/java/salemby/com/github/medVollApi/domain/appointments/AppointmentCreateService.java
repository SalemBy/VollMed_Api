package salemby.com.github.medVollApi.domain.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import salemby.com.github.medVollApi.domain.appointments.validations.IValidateCreateAppointment;
import salemby.com.github.medVollApi.domain.doctors.Doctor;
import salemby.com.github.medVollApi.domain.doctors.DoctorRepository;
import salemby.com.github.medVollApi.domain.exceptions.ValidationItemException;
import salemby.com.github.medVollApi.domain.patient.PatientRepository;

import java.util.List;

@Service
public class AppointmentCreateService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<IValidateCreateAppointment> validators;

    public DataDetailsAppointment createAppointment(DataCreateAppointment data) {

        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationItemException("Patient id not found");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationItemException("Doctor id not found");
        }

        validators.forEach(v -> v.validate(data));

        var patient = patientRepository.getReferenceById(data.idPatient());
        var doctor = findDoctor(data);

        var appointment = new Appointment(null, doctor, patient, data.date());
        appointmentRepository.save(appointment);

        return new DataDetailsAppointment(appointment);
    }

    private Doctor findDoctor(DataCreateAppointment data) {
        if(data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if (data.speciality() == null) {
            throw new ValidationItemException("Speciality not found");
        }

        return doctorRepository.findRandomAvailableDoctorOnDate(data.speciality(), data.date());
    }
}
