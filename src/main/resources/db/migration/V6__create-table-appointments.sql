create table appointments
(

    id          BIGINT   NOT NULL AUTO_INCREMENT,
    doctors_id  BIGINT   NOT NULL,
    patients_id BIGINT   NOT NULL,
    date        DATETIME NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_appointments_doctors_id FOREIGN KEY (doctors_id) REFERENCES doctors (id),
    CONSTRAINT fk_appointments_patients_id FOREIGN KEY (patients_id) REFERENCES patients (id)

);