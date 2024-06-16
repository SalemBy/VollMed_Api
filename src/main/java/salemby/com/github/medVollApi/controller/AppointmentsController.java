package salemby.com.github.medVollApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import salemby.com.github.medVollApi.domain.appointments.AppointmentCreateService;
import salemby.com.github.medVollApi.domain.appointments.DataCreateAppointment;
import salemby.com.github.medVollApi.domain.appointments.DataDetailsAppointment;

@RestController
@RequestMapping("appointment")
public class AppointmentsController {

    @Autowired
    private AppointmentCreateService appointmentCreateService;

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity createAppointment (@RequestBody @Valid DataCreateAppointment data) {

        var dto = appointmentCreateService.createAppointment(data);
        return ResponseEntity.ok(dto);
    }
}
