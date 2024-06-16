package salemby.com.github.medVollApi.domain.doctors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import salemby.com.github.medVollApi.domain.address.AddressData;
import salemby.com.github.medVollApi.domain.appointments.Appointment;
import salemby.com.github.medVollApi.domain.appointments.AppointmentRepository;
import salemby.com.github.medVollApi.domain.patient.Patient;
import salemby.com.github.medVollApi.domain.patient.PatientRegistrationData;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    @DisplayName("Should answer null when the doctor is not available in the date")
    void findRandomAvailableDoctorOnDateOne() {
        var nextMondayAtTenAm = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var doctor = registerDoctor("Jhon Doe", "jhondoe@voll.med", "123456" ,Speciality.CARDIOLOGY);
        var patient = registerPatient("Jhon Doe", "jhondoe@voll.med", "123456789");
        createAppointment(doctor, patient, nextMondayAtTenAm);

        var availableDoctor = doctorRepository.findRandomAvailableDoctorOnDate(Speciality.CARDIOLOGY, nextMondayAtTenAm);

        assertNull(availableDoctor);
    }

    @Test
    @DisplayName("Should answer doctor when the doctor is available in the date")
    void findRandomAvailableDoctorOnDateTwo() {
        var nextMondayAtTenAm = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var doctor = registerDoctor("Jhon Doe", "jhondoe@voll.med", "123456" ,Speciality.CARDIOLOGY);

        var availableDoctor = doctorRepository.findRandomAvailableDoctorOnDate(Speciality.CARDIOLOGY, nextMondayAtTenAm);

        assertEquals(doctor, availableDoctor);
    }


    private void createAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        entityManager.persist(new Appointment(null, doctor, patient, date));
    }

    private Doctor registerDoctor(String name, String email, String crm, Speciality speciality) {
        var doctor = new Doctor(dataDoctor(name, email, crm, speciality));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(dataPatient(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private DoctorRegistrationData dataDoctor(String name, String email, String crm, Speciality speciality) {
        return new DoctorRegistrationData(
                name,
                email,
                "61999999999",
                crm,
                speciality,
                addressData()
        );
    }

    private PatientRegistrationData dataPatient(String name, String email, String cpf) {
        return new PatientRegistrationData(
                name,
                email,
                "61999999999",
                cpf,
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "ex street",
                "neighbourhood",
                "00000000",
                "SP",
                "12345",
                null,
                null
        );
    }
}