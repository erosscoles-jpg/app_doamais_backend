package com.doavida.doavida_api.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.doavida.doavida_api.dto.EmployeeCreateDTO;
import com.doavida.doavida_api.dto.EmployeeResponseDTO;
import com.doavida.doavida_api.dto.EmployeeUpdateDTO;
import com.doavida.doavida_api.model.entities.Employee;
import com.doavida.doavida_api.repositories.EmployeeRepository;
import com.doavida.doavida_api.repositories.UserRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository repository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository repository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public List<EmployeeResponseDTO> findAll() {
        return repository.findAll().stream().map(EmployeeResponseDTO::fromEntity).toList();
    }

    public EmployeeResponseDTO findById(Long id) {
        return EmployeeResponseDTO.fromEntity(getOrThrow(id));
    }

    public EmployeeResponseDTO create(EmployeeCreateDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }
        Employee employee = new Employee(null, dto.userName(), dto.email(),
                passwordEncoder.encode(dto.password()), dto.function());
        return EmployeeResponseDTO.fromEntity(repository.save(employee));
    }

    public EmployeeResponseDTO update(Long id, EmployeeUpdateDTO dto) {
        Employee employee = getOrThrow(id);
        if (!employee.getEmail().equals(dto.email()) && userRepository.existsByEmail(dto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }
        employee.setUserName(dto.userName());
        employee.setEmail(dto.email());
        employee.setFunction(dto.function());
        return EmployeeResponseDTO.fromEntity(repository.save(employee));
    }

    public void delete(Long id) {
        getOrThrow(id);
        repository.deleteById(id);
    }

    private Employee getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
    }
}
