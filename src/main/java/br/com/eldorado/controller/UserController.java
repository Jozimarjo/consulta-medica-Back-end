package br.com.eldorado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eldorado.domain.User;
import br.com.eldorado.service.UserService;



@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	public UserService userService;


	@GetMapping("/{id}")
	public ResponseEntity<?> index(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getOne(id));
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) {
		return ResponseEntity.ok(userService.create(user));

	}

}
