package br.com.eldorado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eldorado.domain.User;
import br.com.eldorado.dto.SessionDTO;
import br.com.eldorado.service.AuthService;

@RestController
@RequestMapping(value = "/session")
@CrossOrigin("*")
public class SessionController {
	@Autowired
	AuthService authService;

	@PostMapping
	public ResponseEntity<?> login(@Validated @RequestBody SessionDTO sessionDTO) {

		return authService.login(sessionDTO);

	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> createAccount(@Validated @RequestBody User user) {

		return authService.createAccount(user);

	}

}
