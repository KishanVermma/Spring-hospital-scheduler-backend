package com.assignment.hospital_appointment_scheduler.controller;

import com.assignment.hospital_appointment_scheduler.dto.AppointmentResp;
import com.assignment.hospital_appointment_scheduler.model.Appointment;
import com.assignment.hospital_appointment_scheduler.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://symbdemo.netlify.app")
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/{specialization}")
    public ResponseEntity<?> bookAppointment(@PathVariable String specialization) {

        if (specialization == null || specialization.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Specialization is required");
        }

        try {
            Appointment appointment = appointmentService.bookAppointment(specialization);

            AppointmentResp response = new AppointmentResp(
                    appointment.getId(),
                    appointment.getDoctor().getDoctorId(),
                    appointment.getDoctor().getSpecialization(),
                    appointment.getAppointmentDate()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
