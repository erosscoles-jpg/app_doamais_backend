package com.doavida.doavida_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DonorUpdateDTO(
		@NotBlank String userName,
		@NotBlank @Email String email) {
}
