package salemby.com.github.medVollApi.doctors;

public record DoctorListDTO(String name, String email, String crm, Speciality speciality, Long id) {
    public DoctorListDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality(), doctor.getId());
    }
}
