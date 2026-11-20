package com.doavida.doavida_api.dto;

import com.doavida.doavida_api.model.entities.Employee;
import com.doavida.doavida_api.model.entities.enums.EmployeeFunction;

public record EmployeeResponseDTO(Long idUser, String userName, String email, EmployeeFunction function) {

	public static EmployeeResponseDTO fromEntity(Employee employee) {
        return new EmployeeResponseDTO(employee.getIdUser(), employee.getUserName(),
                employee.getEmail(), employee.getFunction());
    }
}
