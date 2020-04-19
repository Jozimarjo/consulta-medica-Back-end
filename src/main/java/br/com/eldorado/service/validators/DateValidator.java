package br.com.eldorado.service.validators;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import br.com.eldorado.domain.Appointment;
import br.com.eldorado.repository.AppointmentRepository;

public class DateValidator extends AppointmentValidator {
	private AppointmentRepository repository;

	public DateValidator(AppointmentRepository repository) {
		this.repository = repository;
	}

	@Override
	protected void valida(Appointment registro) {
		ZonedDateTime zdtManaus = ZonedDateTime.now(ZoneId.of("America/Manaus"));

		if (registro.getDate().getMinute() > 0 || registro.getDate().isBefore(zdtManaus.toLocalDateTime())) {
			throw new Error("Data Invalida");

		}

	}

}
