package com.doavida.doavida_api.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Donor extends User {
	private static final long serialVersionUID = 1L;

	private DonorForm form;
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
