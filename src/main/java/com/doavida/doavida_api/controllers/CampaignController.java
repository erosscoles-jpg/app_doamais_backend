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

import com.doavida.doavida_api.dto.CampaignDTO;
import com.doavida.doavida_api.services.CampaignService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

	private final CampaignService service;
	
	public CampaignController(CampaignService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<CampaignDTO> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public CampaignDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<CampaignDTO> create(@Valid @RequestBody CampaignDTO dto) {
		CampaignDTO created = service.create(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(created.idCampaign()).toUri();
		return ResponseEntity.created(location).body(created);
	}
	
	@PutMapping("/{id}")
	public CampaignDTO update(@PathVariable Long id, @Valid @RequestBody CampaignDTO dto) {
		return service.update(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
