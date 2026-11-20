package com.doavida.doavida_api.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_donor")
public class Donor extends User {
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "donor_form_id")
	private DonorForm donorForm;
	
	@OneToMany(mappedBy = "donor")
	private List<Enrollment> enrollments = new ArrayList<>();
	
	public Donor() {
		super();
	}

	public Donor(Long idUser, String userName, String email, String password) {
	    super(idUser, userName, email, password);
	}
	
	public Donor(DonorForm donorForm, List<Enrollment> enrollments) {
		super();
		this.donorForm = donorForm;
		this.enrollments = enrollments;
	}

	public DonorForm getDonorForm() {
		return donorForm;
	}

	public void setDonorForm(DonorForm donorForm) {
		this.donorForm = donorForm;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}
}
