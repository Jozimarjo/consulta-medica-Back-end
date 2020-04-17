package br.com.eldorado.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.eldorado.domain.Appointment;
import br.com.eldorado.domain.Doctor;
import br.com.eldorado.domain.Message;
import br.com.eldorado.domain.User;
import br.com.eldorado.dto.AppointmentDTO;
import br.com.eldorado.dto.UserDTO;
import br.com.eldorado.repository.AppointmentRepository;
import br.com.eldorado.repository.DoctorRepository;
import br.com.eldorado.repository.UserRepository;
import br.com.eldorado.service.validators.AppointmentDoctorValidator;
import br.com.eldorado.service.validators.AppointmentValidator;
import br.com.eldorado.service.validators.DateValidator;
import javassist.expr.NewArray;
import net.bytebuddy.asm.Advice.Local;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository appointRepo;

	@Autowired
	DoctorRepository docRepo;

	@Autowired
	UserRepository userRepo;

	public ResponseEntity<?> create(Appointment appointment) {
		AppointmentValidator validator = new DateValidator(appointRepo);
		validator.link(new AppointmentDoctorValidator(appointRepo));
		validator.executaValidacao(appointment);
		
		try {
			appointRepo.save(appointment);
		} catch (Exception e) {
			throw new Error("Erro ao Criar a Consulta. Atualize a Pagina e tente Novamente");
		}

		return new ResponseEntity(new Message("Consulta Marcada com sucesso"), HttpStatus.ACCEPTED);

	}

	public ResponseEntity<?> show(Long idUser) {
		User user = userRepo.getOne(idUser);

		List<Appointment> appointmentList = appointRepo.findByUser(user);

		List<AppointmentDTO> appDTO = new ArrayList<AppointmentDTO>();

		appointmentList.stream().forEach(app -> {
			ModelMapper obj = new ModelMapper();
			appDTO.add(obj.map(app, AppointmentDTO.class));
		});

		return new ResponseEntity(appDTO, HttpStatus.ACCEPTED);
	}
}
