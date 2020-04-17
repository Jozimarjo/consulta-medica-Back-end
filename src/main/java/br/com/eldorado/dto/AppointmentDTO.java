package br.com.eldorado.dto;

import java.time.LocalDateTime;

import br.com.eldorado.domain.Doctor;

public class AppointmentDTO {
	private Long id;

	private  LocalDateTime date;

	private Doctor doctor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	
}
