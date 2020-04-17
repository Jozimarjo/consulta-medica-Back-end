package br.com.eldorado.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SessionDTO {
	
	@NotBlank
	private String password;

	@Email
	private String email;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
