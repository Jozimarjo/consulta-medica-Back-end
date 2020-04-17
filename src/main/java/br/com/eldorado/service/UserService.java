package br.com.eldorado.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.eldorado.domain.User;
import br.com.eldorado.dto.UserDTO;
import br.com.eldorado.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public UserDTO getOne(Long id) {
		User user = userRepo.getOne(id);
		ModelMapper obj = new ModelMapper();

		return obj.map(user, UserDTO.class);
	}

	public User create(User user) {
		
		return userRepo.save(user);

	}

}
