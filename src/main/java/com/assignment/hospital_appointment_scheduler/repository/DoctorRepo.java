package com.assignment.hospital_appointment_scheduler.repository;

import com.assignment.hospital_appointment_scheduler.model.Doctor;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepo extends JpaRepository<Doctor,Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
    SELECT d
    FROM Doctor d
    LEFT JOIN d.appointments a
    WHERE LOWER(d.specialization) = LOWER(:specialization)
    GROUP BY d
    HAVING COUNT(a) < d.maxDailyPatients
    ORDER BY COUNT(a) ASC
""")
    List<Doctor> findAvailableDoctors(@Param("specialization") String specialization);
}
