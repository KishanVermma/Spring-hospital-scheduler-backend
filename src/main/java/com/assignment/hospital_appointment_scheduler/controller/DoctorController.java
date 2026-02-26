package com.assignment.hospital_appointment_scheduler.controller;

import com.assignment.hospital_appointment_scheduler.dto.AddDoctorRequest;
import com.assignment.hospital_appointment_scheduler.dto.DoctorResponse;
import com.assignment.hospital_appointment_scheduler.model.Doctor;
import com.assignment.hospital_appointment_scheduler.repository.DoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class DoctorController {
    @Autowired
    private DoctorRepo doctorRepository;
    @PostMapping("/doctors")
    public ResponseEntity<?> addDoctor(@RequestBody AddDoctorRequest request) {
        if (request.getSpecialization() == null || request.getSpecialization().trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Specialization is required");
        }

        if (request.getMaxDailyPatients() == null || request.getMaxDailyPatients() < 1) {
            return ResponseEntity
                    .badRequest()
                    .body("Max patients must be at least 1");
        }

        Doctor doctor = new Doctor();
        doctor.setSpecialization(request.getSpecialization());
        doctor.setMaxDailyPatients(request.getMaxDailyPatients());

        Doctor savedDoctor = doctorRepository.save(doctor);

        DoctorResponse response = new DoctorResponse(
                savedDoctor.getDoctorId(),
                savedDoctor.getSpecialization(),
                savedDoctor.getMaxDailyPatients(),
                0L
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/view-all-doctors")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {

        List<DoctorResponse> list = doctorRepository.findAll()
                .stream()
                .map(doc -> new DoctorResponse(
                        doc.getDoctorId(),
                        doc.getSpecialization(),
                        doc.getMaxDailyPatients(),
                        (long) doc.getAppointments().size()
                ))
                .toList();

        return ResponseEntity.ok(list);
    }

}
