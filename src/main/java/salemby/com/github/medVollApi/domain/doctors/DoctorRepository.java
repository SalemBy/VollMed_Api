package salemby.com.github.medVollApi.domain.doctors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.print.Doc;
import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pagination);

    @Query("""
             SELECT d from Doctor d
             WHERE d.active = true
             AND d.speciality = :speciality
             AND d.id NOT IN (
             SELECT a.id FROM Appointment a
             WHERE a.date = :date )
             ORDER BY rand()
             limit 1
            """)
    Doctor findRandomAvailableDoctorOnDate(Speciality speciality, LocalDateTime date);

    @Query("SELECT d.active FROM Doctor d WHERE d.id = :id")
    Boolean findActiveById(Long id);


}
