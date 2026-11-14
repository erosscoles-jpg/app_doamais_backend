package com.doavida.doavida_api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.doavida.doavida_api.dto.DonorCreateDTO;
import com.doavida.doavida_api.dto.DonorResponseDTO;
import com.doavida.doavida_api.dto.DonorUpdateDTO;
import com.doavida.doavida_api.services.DonorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/donors")
public class DonorController {

	private final DonorService service;

	public DonorController(DonorService service) {
		this.service = service;
	}
	
	@GetMapping
    public List<DonorResponseDTO> findAll() {
        return service.findAll();
    }
	
	@GetMapping("/{id}")
	public DonorResponseDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<DonorResponseDTO> create(@Valid @RequestBody DonorCreateDTO dto) {
		DonorResponseDTO created = service.create(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(created.idUser()).toUri();
		return ResponseEntity.created(location).body(created);
	}
	
	@PutMapping("/{id}")
	public DonorResponseDTO update(@PathVariable Long id, @Valid @RequestBody DonorUpdateDTO dto) {
		return service.update(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
