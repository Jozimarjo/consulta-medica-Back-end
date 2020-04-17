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
		Doctor doctor = null;
		User user = null;
		Boolean isValid = false;

		if (appointment.getDoctor() != null)
			doctor = docRepo.getOne(appointment.getDoctor().getId());

		if (appointment.getUser() != null)
			user = userRepo.getOne(appointment.getUser().getId());

		if (doctor == null)
			return new ResponseEntity(appointment, HttpStatus.BAD_REQUEST);

		if (appointment.getDate().isBefore(LocalDateTime.now())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HORARIO JA PASSOU");
		}

		List<Appointment> appointmentList = appointRepo.findByDoctor(doctor);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		isValid = appointmentList.stream().anyMatch(appoint -> {
			if (appoint.getDate().format(formatter).equals(appointment.getDate().format(formatter))
					&& appoint.getDate().getHour() == appointment.getDate().getHour()) {
				return true;
			}
			return false;

		});

		if (isValid == true) {
			return new ResponseEntity(appointment, HttpStatus.BAD_REQUEST);
		}

		Appointment ap = new Appointment();
		ap.setDoctor(doctor);
		ap.setUser(user);
		ap.setDate(appointment.getDate());
		
		try {
			appointRepo.save(ap);			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}


		return new ResponseEntity(new Message("Erro ao tentar marcar"), HttpStatus.BAD_REQUEST);

//		return new ResponseEntity(new Message("Consulta Marcada com sucesso"), HttpStatus.ACCEPTED);

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
