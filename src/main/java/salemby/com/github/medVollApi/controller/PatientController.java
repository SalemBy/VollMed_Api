package salemby.com.github.medVollApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import salemby.com.github.medVollApi.domain.patient.*;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional

    public ResponseEntity registerPatient(@RequestBody @Valid PatientRegistrationData data, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        patientRepository.save(patient);

        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailsPatient(patient));

    }

    @GetMapping
    public ResponseEntity<Page<DataListPatient>> listPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = patientRepository.findAllByActiveTrue(pagination).map(DataListPatient::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePatients(@RequestBody @Valid DataUpdatePatient data) {
        var patient = patientRepository.getReferenceById(data.id());
        patient.updatePatient(data);

        return ResponseEntity.ok(new DataDetailsPatient(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity logicalDelete(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.deletePatient();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity patientDetails(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailsPatient(patient));
    }
}
