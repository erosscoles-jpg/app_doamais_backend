package com.doavida.doavida_api.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.doavida.doavida_api.model.entities.enums.EnrollmentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_enrollment")
public class Enrollment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEnrollment;
	private LocalDateTime enrollmentDate;
	
	@Enumerated(EnumType.STRING)
	private EnrollmentStatus status;
	
	@ManyToOne
	@JoinColumn(name = "donor_id")
	private Donor donor;
	
	@ManyToOne
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;

	public Enrollment() {
	}

	public Enrollment(Long idEnrollment, LocalDateTime enrollmentDate, EnrollmentStatus status, Donor donor, Campaign campaign) {
		this.idEnrollment = idEnrollment;
		this.enrollmentDate = enrollmentDate;
		this.status = status;
		this.donor = donor;
		this.campaign = campaign;
	}

	public Long getIdEnrollment() {
		return idEnrollment;
	}

	public LocalDateTime getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setDate(LocalDateTime enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
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
