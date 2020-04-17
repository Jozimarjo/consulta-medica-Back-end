package br.com.eldorado.service.validators;

import java.time.LocalDateTime;

import br.com.eldorado.domain.Appointment;
import br.com.eldorado.repository.AppointmentRepository;

public class DateValidator extends AppointmentValidator {
	private AppointmentRepository repository;

	public DateValidator(AppointmentRepository repository) {
		this.repository = repository;
	}

	@Override
	protected void valida(Appointment registro) {
		if (registro.getDate().getMinute() > 0 || registro.getDate().isBefore(LocalDateTime.now())) {
			throw new Error("Data Invalida");

		}

	}

}
