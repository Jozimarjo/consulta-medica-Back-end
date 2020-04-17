package br.com.eldorado.service.validators;

import br.com.eldorado.domain.Appointment;
import br.com.eldorado.repository.AppointmentRepository;
import br.com.eldorado.repository.DoctorRepository;
import br.com.eldorado.repository.UserRepository;

public class AppointmentDoctorValidator extends AppointmentValidator {
	private AppointmentRepository repository;
	private DoctorRepository docRepository;
	private UserRepository userRepository;

	public AppointmentDoctorValidator(AppointmentRepository repository) {
		this.repository = repository;

	}

	@Override
	protected void valida(Appointment registro) {
		// TODO Auto-generated method stub
			if(repository.findByDoctorAndDate(registro.getDoctor(), registro.getDate())!=null) {
				throw new Error("JA EXISTE UMA CONSULTA NESSA DATA ");
			}
	}
}
