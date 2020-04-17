package br.com.eldorado.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eldorado.domain.Appointment;
import br.com.eldorado.domain.Doctor;
import br.com.eldorado.domain.User;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findByDoctor(Doctor doctor);

	Appointment findByDoctorAndDate(Doctor doctor, LocalDateTime date);

	List<Appointment> findByUser(User user);

}
