package br.com.eldorado.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eldorado.service.AvailableAppointmentService;
import br.com.eldorado.service.DoctorService;

@RestController
@RequestMapping(value = "/availableAppointment")
@CrossOrigin("*")
public class AvailableAppointmentController {

	@Autowired
	public AvailableAppointmentService availableService;

	@GetMapping("/{idDoctor}/{date}")
	public ResponseEntity<?> show(@PathVariable Long idDoctor,
			@PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDate date) {

		return availableService.index(idDoctor, date);

	}

}
