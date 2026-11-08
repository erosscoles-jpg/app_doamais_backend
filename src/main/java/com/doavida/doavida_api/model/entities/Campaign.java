package com.doavida.doavida_api.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Campaign implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idCampaign;
	private String campaignName;
	private String location;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	private List<Enrollment> enrollments = new ArrayList<>();
	
	public Campaign() {
	}

	public Campaign(Long idCampaign, String campaignName, String location, LocalDateTime startDate, LocalDateTime endDate) {
		this.idCampaign = idCampaign;
		this.campaignName = campaignName;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getIdCampaign() {
		return idCampaign;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCampaign);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Campaign other = (Campaign) obj;
		return Objects.equals(idCampaign, other.idCampaign);
	}
}
