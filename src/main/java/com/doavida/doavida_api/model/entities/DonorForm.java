package com.doavida.doavida_api.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.doavida.doavida_api.model.entities.enums.BloodType;
import com.doavida.doavida_api.model.entities.enums.FormStatus;

import jakarta.persistence.Column;
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
@Table(name = "tb_donor_form")
public class DonorForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idForm;
	private LocalDate birthDate;
	
	@Enumerated(EnumType.STRING)
	private BloodType bloodType;
	private Double height;
	private Double weight;
	
	@Enumerated(EnumType.STRING)
	private FormStatus status;
	private LocalDateTime evaluationDate;
	
	@Column(length = 500)
	private String observation;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	public DonorForm() {
	}
	
	public DonorForm(Long idForm, LocalDate birthDate, BloodType bloodType, Double height, Double weight,
			FormStatus status, LocalDateTime evaluationDate, String observation, Employee employee) {
		super();
		this.idForm = idForm;
		this.birthDate = birthDate;
		this.bloodType = bloodType;
		this.height = height;
		this.weight = weight;
		this.status = status;
		this.evaluationDate = evaluationDate;
		this.observation = observation;
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
	
	public BloodType getBloodType() {
		return bloodType;
	}

	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
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
	
	public FormStatus getStatus() {
		return status;
	}

	public void setStatus(FormStatus status) {
		this.status = status;
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
