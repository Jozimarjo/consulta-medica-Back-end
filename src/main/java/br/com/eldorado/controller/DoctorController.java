package br.com.eldorado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eldorado.domain.Doctor;
import br.com.eldorado.service.DoctorService;


@RestController
@RequestMapping(value = "/doctor")
@CrossOrigin("*")
public class DoctorController {

	@Autowired
	public DoctorService docService;


	@GetMapping
	public ResponseEntity<?> show() {
		return ResponseEntity.ok(docService.show());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Doctor doctor) {
		return ResponseEntity.ok(docService.create(doctor));

	}
}
