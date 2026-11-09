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
	private DonorForm form;
	
	@OneToMany(mappedBy = "donor")
	private List<Enrollment> enrollments = new ArrayList<>();
	
	public Donor() {
		super();
	}

	public Donor(DonorForm form, List<Enrollment> enrollments) {
		super();
		this.form = form;
		this.enrollments = enrollments;
	}

	public DonorForm getForm() {
		return form;
	}

	public void setForm(DonorForm form) {
		this.form = form;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}
}
