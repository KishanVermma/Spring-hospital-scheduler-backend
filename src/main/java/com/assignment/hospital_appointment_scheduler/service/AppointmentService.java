package com.assignment.hospital_appointment_scheduler.service;

import com.assignment.hospital_appointment_scheduler.dto.AppointmentResp;
import com.assignment.hospital_appointment_scheduler.model.Appointment;
import com.assignment.hospital_appointment_scheduler.model.Doctor;
import com.assignment.hospital_appointment_scheduler.repository.AppointmentRepo;
import com.assignment.hospital_appointment_scheduler.repository.DoctorRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final DoctorRepo doctorRepository;
    private final AppointmentRepo appointmentRepository;

    @Transactional
    public Appointment bookAppointment(String specialization) {

        List<Doctor> doctors =
                doctorRepository.findAvailableDoctors(specialization);

        if (doctors.isEmpty()) {
            throw new IllegalStateException("All doctors are fully booked for today");
        }

        Doctor doctor = doctors.get(0);

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(LocalDate.now());

        return appointmentRepository.save(appointment);
    }
}
