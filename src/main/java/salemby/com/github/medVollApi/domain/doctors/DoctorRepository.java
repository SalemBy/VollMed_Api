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
                select d from Doctor d
                where
                d.active = true 
                and
                d.speciality = :speciality
                and
                d.id not in(
                    select a.doctor.id from Appointment a
                    where
                    a.date = :date
                )
                order by rand()
                limit 1
            """)
    Doctor findRandomAvailableDoctorOnDate(Speciality speciality, LocalDateTime date);

    @Query("SELECT d.active FROM Doctor d WHERE d.id = :id")
    Boolean findActiveById(Long id);


}
