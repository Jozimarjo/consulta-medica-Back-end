package br.com.eldorado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eldorado.domain.Doctor;
import br.com.eldorado.repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository docRepository;
	
	public Doctor create(Doctor doctor) {
		return docRepository.save(doctor);
	}
	
	public List<Doctor> show() {
		return docRepository.findAll();
		
	}
	
	
}
