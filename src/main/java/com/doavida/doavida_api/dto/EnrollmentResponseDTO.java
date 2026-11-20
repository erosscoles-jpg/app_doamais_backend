package com.doavida.doavida_api.dto;

import java.time.LocalDateTime;

import com.doavida.doavida_api.model.entities.Enrollment;
import com.doavida.doavida_api.model.entities.enums.EnrollmentStatus;

public record EnrollmentResponseDTO(
		Long idEnrollment,
        Long donorId,
        String donorName,
        Long campaignId,
        String campaignName,
        LocalDateTime enrollmentDate,
        EnrollmentStatus status) {

	public static EnrollmentResponseDTO fromEntity(Enrollment enrollment) {
        return new EnrollmentResponseDTO(
                enrollment.getIdEnrollment(),
                enrollment.getDonor().getIdUser(),
                enrollment.getDonor().getUserName(),
                enrollment.getCampaign().getIdCampaign(),
                enrollment.getCampaign().getCampaignName(),
                enrollment.getEnrollmentDate(),
                enrollment.getStatus());
    }
}
