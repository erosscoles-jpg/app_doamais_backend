package com.doavida.doavida_api.dto;

import java.time.LocalDate;

import com.doavida.doavida_api.model.entities.enums.BloodType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

public record DonorFormRequestDTO(
		@NotNull @Past LocalDate birthDate,
		@NotNull BloodType bloodType,
		@NotNull @Positive Double height,
		@NotNull @Positive Double weight) {

}
