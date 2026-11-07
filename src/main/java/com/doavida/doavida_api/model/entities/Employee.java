package com.doavida.doavida_api.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.doavida.doavida_api.model.entities.enums.EmployeeFunction;

public class Employee extends User {
	private static final long serialVersionUID = 1L;

	private EmployeeFunction function;
	
	private List<DonorForm> evaluatedForm = new ArrayList<>();

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
		return evaluatedForm;
	}
}
