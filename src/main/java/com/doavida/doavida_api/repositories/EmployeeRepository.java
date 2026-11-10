package com.doavida.doavida_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doavida.doavida_api.model.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
