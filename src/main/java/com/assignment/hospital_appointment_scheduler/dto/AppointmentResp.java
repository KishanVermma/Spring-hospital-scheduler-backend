package com.assignment.hospital_appointment_scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResp {
    private Long appointmentId;
    private Long doctorId;
    private String specialization;
    private LocalDate appointmentDate;
}
