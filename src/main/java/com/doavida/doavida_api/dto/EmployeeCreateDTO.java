package com.doavida.doavida_api.dto;

import com.doavida.doavida_api.model.entities.enums.EmployeeFunction;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeCreateDTO(
		@NotBlank String userName,
		@NotBlank @Email String email,
		@NotBlank @Size(min = 6) String password,
		@NotNull EmployeeFunction function) {
}
