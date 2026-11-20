package com.doavida.doavida_api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.doavida.doavida_api.model.entities.Donor;
import com.doavida.doavida_api.model.entities.DonorForm;
import com.doavida.doavida_api.model.entities.Employee;
import com.doavida.doavida_api.model.entities.enums.BloodType;
import com.doavida.doavida_api.model.entities.enums.FormStatus;

public record DonorFormResponseDTO(
		Long donorId,
        String donorName,
        LocalDate birthDate,
        BloodType bloodType,
        Double height,
        Double weight,
        FormStatus status,
        LocalDateTime evaluationDate,
        String observation,
        String evaluatedBy) {

	public static DonorFormResponseDTO fromDonor(Donor donor) {
		DonorForm form = donor.getDonorForm();
		Employee employee = form.getEmployee();
		return new DonorFormResponseDTO(
				donor.getIdUser(),
                donor.getUserName(),
                form.getBirthDate(),
                form.getBloodType(),
                form.getHeight(),
                form.getWeight(),
                form.getStatus(),
                form.getEvaluationDate(),
                form.getObservation(),
                employee == null ? null : employee.getUserName());
	}
}
