package com.doavida.doavida_api.dto;

import java.time.LocalDateTime;

import com.doavida.doavida_api.model.entities.Campaign;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CampaignDTO(
		Long idCampaign,
		@NotBlank String campaignName,
		@NotBlank String location,
		@NotNull LocalDateTime startDate,
		@NotNull LocalDateTime endDate) {

	public static CampaignDTO fromEntity(Campaign campaign) {
		return new CampaignDTO(
				campaign.getIdCampaign(),
				campaign.getCampaignName(),
				campaign.getLocation(),
				campaign.getStartDate(),
				campaign.getEndDate());
	}
	
	public Campaign toEntity() {
		return new Campaign(idCampaign, campaignName, location, startDate, endDate);
	}
}
