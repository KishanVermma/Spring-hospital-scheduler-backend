package com.assignment.hospital_appointment_scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private Long doctorId;
    private String specialization;
    private Integer maxDailyPatients;
    private Long currentAppointments;
}
