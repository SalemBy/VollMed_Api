package salemby.com.github.medVollApi.domain.appointments;

import jakarta.persistence.*;
import lombok.*;
import salemby.com.github.medVollApi.domain.doctors.Doctor;
import salemby.com.github.medVollApi.domain.patient.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "appointments")
@Entity(name = "Appointment")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctors_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patients_id")
    private Patient patient;

    private LocalDateTime date;
}
