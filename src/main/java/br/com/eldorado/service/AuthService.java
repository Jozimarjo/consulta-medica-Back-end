package br.com.eldorado.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.eldorado.domain.User;
import br.com.eldorado.dto.SessionDTO;
import br.com.eldorado.dto.UserDTO;
import br.com.eldorado.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	UserRepository userRepo;

	public ResponseEntity<?> login(SessionDTO session) {
		User user = userRepo.findByEmail(session.getEmail());

		if (user == null) {
			return new ResponseEntity<Object>("Login não Existe", HttpStatus.UNAUTHORIZED);
		}

		if (user.getPassword().equals(session.getPassword())) {
			ModelMapper obj = new ModelMapper();
			return ResponseEntity.ok(obj.map(user, UserDTO.class));
		}
		return new ResponseEntity<Object>("Login ou Senha Incorretos", HttpStatus.UNAUTHORIZED);

	}

	public ResponseEntity<?> createAccount(User user) {
		User userExist = userRepo.findByEmail(user.getEmail());
		if (userExist != null)
			return new ResponseEntity<Object>("Email ja castrado", HttpStatus.BAD_REQUEST);
		userRepo.save(user);
		return new ResponseEntity<Object>(user, HttpStatus.CREATED);
	}
}
