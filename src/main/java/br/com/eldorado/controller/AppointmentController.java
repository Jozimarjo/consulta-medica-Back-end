package br.com.eldorado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eldorado.domain.Appointment;
import br.com.eldorado.domain.User;
import br.com.eldorado.repository.AppointmentRepository;
import br.com.eldorado.repository.UserRepository;
import br.com.eldorado.service.AppointmentService;

@RestController
@RequestMapping(value = "/appointment")
@CrossOrigin("*")
public class AppointmentController {

	@Autowired
	public AppointmentRepository appointmentRepo;

	@Autowired
	public UserRepository userRepo;

	@Autowired
	public AppointmentService appointmentService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Appointment appointment) {

		return appointmentService.create(appointment);

	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> findAll(@PathVariable Long userId) {
		return ResponseEntity.ok(appointmentService.show(userId));
	}

}
