package com.assignment.hospital_appointment_scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDoctorRequest {
    private String specialization;

    @NotNull
    private Integer maxDailyPatients;
}
