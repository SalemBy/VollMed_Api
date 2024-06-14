package salemby.com.github.medVollApi.domain.doctors;

import salemby.com.github.medVollApi.domain.address.Address;

public record DetailsDoctorList(Long id, String name, String email, String crm, Speciality speciality, Address address) {

    public DetailsDoctorList(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality(), doctor.getAddress());
    }
}
