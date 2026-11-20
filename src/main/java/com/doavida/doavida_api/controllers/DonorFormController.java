package com.doavida.doavida_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doavida.doavida_api.dto.DonorFormEvaluationDTO;
import com.doavida.doavida_api.dto.DonorFormRequestDTO;
import com.doavida.doavida_api.dto.DonorFormResponseDTO;
import com.doavida.doavida_api.model.entities.enums.FormStatus;
import com.doavida.doavida_api.services.DonorFormService;

import jakarta.validation.Valid;

@RestController
public class DonorFormController {

	private final DonorFormService service;

	public DonorFormController(DonorFormService service) {
        this.service = service;
    }
	
	@GetMapping("/donors/{donorId}/form")
    public DonorFormResponseDTO find(@PathVariable Long donorId) {
        return service.find(donorId);
    }
	
	@PutMapping("/donors/{donorId}/form")
    public DonorFormResponseDTO save(@PathVariable Long donorId, @Valid @RequestBody DonorFormRequestDTO dto) {
        return service.save(donorId, dto);
    }

    @PutMapping("/donors/{donorId}/form/evaluation")
    public DonorFormResponseDTO evaluate(@PathVariable Long donorId, @Valid @RequestBody DonorFormEvaluationDTO dto) {
        return service.evaluate(donorId, dto);
    }

    @GetMapping("/donor-forms")
    public List<DonorFormResponseDTO> findAll(@RequestParam(required = false) FormStatus status) {
        return service.findAll(status);
    }
}
