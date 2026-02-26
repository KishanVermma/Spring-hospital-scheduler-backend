package com.assignment.hospital_appointment_scheduler.repository;

import com.assignment.hospital_appointment_scheduler.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    Long countByDoctorDoctorId(Long doctorId);
}
