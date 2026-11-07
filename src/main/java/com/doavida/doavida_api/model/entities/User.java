package com.doavida.doavida_api.model.entities;

import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idUser;
	private String userName;
	private String email;
	private String password;
	
	public User() {
	}

	public User(Long idUser, String userName, String email, String password) {
		this.idUser = idUser;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public Long getIdUser() {
		return idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUser);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		User other = (User) obj;
		return Objects.equals(idUser, other.idUser);
	}
}
