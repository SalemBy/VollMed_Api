package salemby.com.github.medVollApi.domain.appointments;

import java.time.LocalDateTime;

public record DataDetailsAppointment(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
    public DataDetailsAppointment(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }
}
