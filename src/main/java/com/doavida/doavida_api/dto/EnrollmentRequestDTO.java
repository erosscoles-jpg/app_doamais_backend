package com.doavida.doavida_api.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record EnrollmentRequestDTO(
		@NotNull Long donorId,
        @NotNull Long campaignId,
        @NotNull @Future LocalDateTime enrollmentDate) {

}
