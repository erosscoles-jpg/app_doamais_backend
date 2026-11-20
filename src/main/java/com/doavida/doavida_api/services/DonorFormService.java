package com.doavida.doavida_api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.doavida.doavida_api.dto.DonorFormEvaluationDTO;
import com.doavida.doavida_api.dto.DonorFormRequestDTO;
import com.doavida.doavida_api.dto.DonorFormResponseDTO;
import com.doavida.doavida_api.model.entities.Donor;
import com.doavida.doavida_api.model.entities.DonorForm;
import com.doavida.doavida_api.model.entities.Employee;
import com.doavida.doavida_api.model.entities.enums.FormStatus;
import com.doavida.doavida_api.repositories.DonorRepository;
import com.doavida.doavida_api.repositories.EmployeeRepository;

@Service
public class DonorFormService {

	private final DonorRepository donorRepository;
    private final EmployeeRepository employeeRepository;

    public DonorFormService(DonorRepository donorRepository, EmployeeRepository employeeRepository) {
        this.donorRepository = donorRepository;
        this.employeeRepository = employeeRepository;
    }
    
    public DonorFormResponseDTO find(Long donorId) {
        Donor donor = getDonor(donorId);
        if (donor.getDonorForm() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O doador ainda não preencheu a ficha");
        }
        return DonorFormResponseDTO.fromDonor(donor);
    }

    public List<DonorFormResponseDTO> findAll(FormStatus status) {
        List<Donor> donors = (status == null)
                ? donorRepository.findByDonorFormIsNotNull()
                : donorRepository.findByDonorFormStatus(status);
        return donors.stream().map(DonorFormResponseDTO::fromDonor).toList();
    }
    
    public DonorFormResponseDTO save(Long donorId, DonorFormRequestDTO dto) {
        Donor donor = getDonor(donorId);
        DonorForm form = donor.getDonorForm();
        if (form == null) {
            form = new DonorForm();
            donor.setDonorForm(form);
        }
        form.setBirthDate(dto.birthDate());
        form.setBloodType(dto.bloodType());
        form.setHeight(dto.height());
        form.setWeight(dto.weight());
        form.setStatus(FormStatus.PENDING);
        form.setEvaluationDate(null);
        form.setObservation(null);
        form.setEmployee(null);
        return DonorFormResponseDTO.fromDonor(donorRepository.save(donor));
    }
    
    public DonorFormResponseDTO evaluate(Long donorId, DonorFormEvaluationDTO dto) {
        Donor donor = getDonor(donorId);
        DonorForm form = donor.getDonorForm();
        if (form == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O doador ainda não preencheu a ficha");
        }
        if (dto.status() == FormStatus.PENDING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A avaliação não pode voltar a ficha para pendente");
        }
        Employee employee = employeeRepository.findById(dto.employeeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
        form.setStatus(dto.status());
        form.setObservation(dto.observation());
        form.setEvaluationDate(LocalDateTime.now());
        form.setEmployee(employee);
        return DonorFormResponseDTO.fromDonor(donorRepository.save(donor));
    }
    
    private Donor getDonor(Long id) {
        return donorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doador não encontrado"));
    }
}
