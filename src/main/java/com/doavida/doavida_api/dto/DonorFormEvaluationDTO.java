package com.doavida.doavida_api.dto;

import com.doavida.doavida_api.model.entities.enums.FormStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DonorFormEvaluationDTO(
		@NotNull Long employeeId,
		@NotNull FormStatus status,
		@Size(max = 500) String observation) {

}
