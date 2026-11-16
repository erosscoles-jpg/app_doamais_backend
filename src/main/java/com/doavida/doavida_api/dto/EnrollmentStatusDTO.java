package com.doavida.doavida_api.dto;

import com.doavida.doavida_api.model.entities.enums.EnrollmentStatus;

import jakarta.validation.constraints.NotNull;

public record EnrollmentStatusDTO(@NotNull EnrollmentStatus status) {

}
