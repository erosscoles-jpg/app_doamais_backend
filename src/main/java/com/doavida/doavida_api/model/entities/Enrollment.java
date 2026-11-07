package com.doavida.doavida_api.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.doavida.doavida_api.model.entities.enums.EnrollmentStatus;

public class Enrollment implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idEnrollment;
	private LocalDateTime date;
	private EnrollmentStatus status;
	
	private Donor donor;
	private Campaign campaign;

	public Enrollment() {
	}

	public Enrollment(Long idEnrollment, LocalDateTime date, EnrollmentStatus status, Donor donor, Campaign campaign) {
		this.idEnrollment = idEnrollment;
		this.date = date;
		this.status = status;
		this.donor = donor;
		this.campaign = campaign;
	}

	public Long getIdEnrollment() {
		return idEnrollment;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public EnrollmentStatus getStatus() {
		return status;
	}

	public void setStatus(EnrollmentStatus status) {
		this.status = status;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

    @Override
    public int hashCode() {
        return Objects.hash(idEnrollment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Enrollment other = (Enrollment) obj;
        return Objects.equals(idEnrollment, other.idEnrollment);
    }
}
