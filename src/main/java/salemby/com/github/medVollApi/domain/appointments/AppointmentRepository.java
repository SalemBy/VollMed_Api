package salemby.com.github.medVollApi.domain.appointments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByIdAndDate(Long id, LocalDateTime date);

    boolean existsByPatientIdAndDateBetween(Long id, LocalDateTime firstHour, LocalDateTime lastHour);

}
