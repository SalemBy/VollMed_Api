package salemby.com.github.medVollApi.domain.patient;

import salemby.com.github.medVollApi.domain.address.Address;

public record DataDetailsPatient(Long id, String name, String email, String cpf, String phone, Address address) {

    public DataDetailsPatient(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf(), patient.getPhone(), patient.getAddress());
    }
}
