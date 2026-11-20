package com.doavida.doavida_api.dto;

import com.doavida.doavida_api.model.entities.Donor;

public record DonorResponseDTO(Long idUser, String userName, String email) {

	public static DonorResponseDTO fromEntity(Donor donor) {
		return new DonorResponseDTO(donor.getIdUser(), donor.getUserName(), donor.getEmail());
	}
}
