package com.doavida.doavida_api.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.doavida.doavida_api.model.entities.enums.EmployeeFunction;

@Entity
@Table(name = "tb_employee")
public class Employee extends User {
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "employee_function")
	private EmployeeFunction function;
	
	@OneToMany(mappedBy = "employee")
	private List<DonorForm> evaluatedForms = new ArrayList<>();

	public Employee() {
		super();
	}

	public Employee(EmployeeFunction function) {
		super();
		this.function = function;
	}

	public EmployeeFunction getFunction() {
		return function;
	}

	public void setFunction(EmployeeFunction function) {
		this.function = function;
	}

	public List<DonorForm> getEvaluatedForm() {
		return evaluatedForms;
	}
}
