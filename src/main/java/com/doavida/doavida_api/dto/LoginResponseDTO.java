package com.doavida.doavida_api.dto;

import com.doavida.doavida_api.model.entities.Employee;
import com.doavida.doavida_api.model.entities.User;
import com.doavida.doavida_api.model.entities.enums.EmployeeFunction;

public record LoginResponseDTO(
		Long idUser,
        String userName,
        String email,
        String userType,
        EmployeeFunction function) {

	public static LoginResponseDTO fromUser(User user) {
        if (user instanceof Employee employee) {
            return new LoginResponseDTO(user.getIdUser(), user.getUserName(), user.getEmail(),
                    "EMPLOYEE", employee.getFunction());
        }
        return new LoginResponseDTO(user.getIdUser(), user.getUserName(), user.getEmail(), "DONOR", null);
    }
}
