package salemby.com.github.medVollApi.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import salemby.com.github.medVollApi.domain.appointments.AppointmentCreateService;
import salemby.com.github.medVollApi.domain.appointments.DataCreateAppointment;
import salemby.com.github.medVollApi.domain.appointments.DataDetailsAppointment;
import salemby.com.github.medVollApi.domain.doctors.Speciality;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DataCreateAppointment> dataCreateAppointmentJson;

    @Autowired
    private JacksonTester<DataDetailsAppointment> dataDetailsAppointmentJson;

    @MockBean
    private AppointmentCreateService appointmentService;

    @Test
    @WithMockUser
    @DisplayName("Should answer with code HTTP 400 when there is invalid information in json")
    void createAppointmentOne() throws Exception {
        var response = mvc.perform(post("/appointment")).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @WithMockUser
    @DisplayName("Should answer with code HTTP 200 when there is valid information in json")
    void createAppointmentTwo() throws Exception {

        var date = LocalDateTime.now().plusHours(1);
        var speciality = Speciality.CARDIOLOGY;

        var dataDetails = new DataDetailsAppointment(null, 3L, 1L, date);

        when(appointmentService.createAppointment(any())).thenReturn(dataDetails);

        var response = mvc.perform(post("/appointment").contentType(MediaType.APPLICATION_JSON)
                .content(dataCreateAppointmentJson.write(
                        new DataCreateAppointment(3L, 1L, date, speciality)
                ).getJson())).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        var expectedJson = dataDetailsAppointmentJson.write(dataDetails).getJson();

        assertEquals(expectedJson, response.getContentAsString());
    }
}