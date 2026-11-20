package com.doavida.doavida_api.dto;

import com.doavida.doavida_api.model.entities.enums.EmployeeFunction;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeUpdateDTO(
		@NotBlank String userName,
        @NotBlank @Email String email,
        @NotNull EmployeeFunction function) {
}
