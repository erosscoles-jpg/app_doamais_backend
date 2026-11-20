package com.doavida.doavida_api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.doavida.doavida_api.dto.EnrollmentRequestDTO;
import com.doavida.doavida_api.dto.EnrollmentResponseDTO;
import com.doavida.doavida_api.dto.EnrollmentStatusDTO;
import com.doavida.doavida_api.services.EnrollmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

	private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }
    
    @GetMapping
    public List<EnrollmentResponseDTO> findAll(@RequestParam(required = false) Long donorId,
            @RequestParam(required = false) Long campaignId) {
        return service.findAll(donorId, campaignId);
    }

    @GetMapping("/{id}")
    public EnrollmentResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> create(@Valid @RequestBody EnrollmentRequestDTO dto) {
        EnrollmentResponseDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.idEnrollment()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PatchMapping("/{id}/status")
    public EnrollmentResponseDTO updateStatus(@PathVariable Long id, @Valid @RequestBody EnrollmentStatusDTO dto) {
        return service.updateStatus(id, dto.status());
    }
}
