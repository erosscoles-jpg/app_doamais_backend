package com.doavida.doavida_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DonorCreateDTO(
		@NotBlank String userName,
		@NotBlank @Email String email,
		@NotBlank @Size(min = 6) String password) {
}
