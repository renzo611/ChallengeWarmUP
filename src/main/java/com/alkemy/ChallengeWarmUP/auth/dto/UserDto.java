package com.alkemy.ChallengeWarmUP.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserDto {
    @Email(message = "El nombre de usuario debe ser un correo electronico")
    private String username;
    private String password;
    
    
	public UserDto(@Email(message = "El nombre de usuario debe ser un correo electronico") String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
