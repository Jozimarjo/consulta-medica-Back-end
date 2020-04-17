package br.com.eldorado.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.eldorado.domain.Appointment;
import br.com.eldorado.domain.Doctor;
import br.com.eldorado.repository.AppointmentRepository;
import br.com.eldorado.repository.DoctorRepository;
import br.com.eldorado.repository.UserRepository;

@Service
public class AvailableAppointmentService {
	@Autowired
	AppointmentRepository appointRepo;

	@Autowired
	DoctorRepository docRepo;

	@Autowired
	UserRepository userRepo;

	public ResponseEntity<?> index(Long idDoctor, LocalDate date) {
		Doctor doctor = docRepo.getOne(idDoctor);
		List<Appointment> appointmentList = appointRepo.findByDoctor(doctor);

		List<Integer> agenda = new ArrayList(Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		appointmentList.stream().forEach(ap -> {
			int index = -1;
			if (ap.getDate().format(formatter).equals(date.toString()) && agenda.contains(ap.getDate().getHour())) {
				index = agenda.indexOf(ap.getDate().getHour());
				agenda.remove(index);

			}

		});

		return new ResponseEntity(agenda, HttpStatus.ACCEPTED);
	}
}
