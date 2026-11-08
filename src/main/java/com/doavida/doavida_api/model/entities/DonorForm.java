package com.doavida.doavida_api.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.doavida.doavida_api.model.entities.enums.BloodType;
import com.doavida.doavida_api.model.entities.enums.FormStatus;

public class DonorForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idForm;
	private LocalDate birthDate;
	private Double height;
	private Double weight;
	private LocalDateTime evaluationDate;
	private String observation;
	private BloodType bloodType;
	private FormStatus status;
	
	private Employee employee;
	
	public DonorForm() {
	}

	public DonorForm(Long idForm, LocalDate birthDate, Double height, Double weight, LocalDateTime evaluationDate,
			String observation, BloodType bloodType, FormStatus status, Employee employee) {
		this.idForm = idForm;
		this.birthDate = birthDate;
		this.height = height;
		this.weight = weight;
		this.evaluationDate = evaluationDate;
		this.observation = observation;
		this.bloodType = bloodType;
		this.status = status;
		this.employee = employee;
	}
	
	public Long getIdForm() {
		return idForm;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public LocalDateTime getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(LocalDateTime evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public BloodType getBloodType() {
		return bloodType;
	}

	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}

	public FormStatus getStatus() {
		return status;
	}

	public void setStatus(FormStatus status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idForm);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		DonorForm other = (DonorForm) obj;
		return Objects.equals(idForm, other.idForm);
	}
}
