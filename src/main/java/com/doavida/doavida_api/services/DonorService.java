package com.doavida.doavida_api.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.doavida.doavida_api.dto.DonorCreateDTO;
import com.doavida.doavida_api.dto.DonorResponseDTO;
import com.doavida.doavida_api.dto.DonorUpdateDTO;
import com.doavida.doavida_api.model.entities.Donor;
import com.doavida.doavida_api.repositories.DonorRepository;

@Service
public class DonorService {

	private final DonorRepository repository;
	private final PasswordEncoder passwordEncoder;
	
	public DonorService(DonorRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public List<DonorResponseDTO> findAll() {
		return repository.findAll().stream().map(DonorResponseDTO::fromEntity).toList();
	}
	
	public DonorResponseDTO findById(Long id) {
		return DonorResponseDTO.fromEntity(getOrThrow(id));
	}
	
	public DonorResponseDTO create(DonorCreateDTO dto) {
		if (repository.existsByEmail(dto.email())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
		}
		Donor donor = new Donor(null, dto.userName(), dto.email(), passwordEncoder.encode(dto.password()));
		return DonorResponseDTO.fromEntity(repository.save(donor));
	}
	
	public DonorResponseDTO update(Long id, DonorUpdateDTO dto) {
		Donor donor = getOrThrow(id);
		if (!donor.getEmail().equals(dto.email()) && repository.existsByEmail(dto.email())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
		}
		donor.setUserName(dto.userName());
		donor.setEmail(dto.email());
		return DonorResponseDTO.fromEntity(repository.save(donor));
	}
	
	public void delete(Long id) {
		getOrThrow(id);
		repository.deleteById(id);
	}
	
	private Donor getOrThrow(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doador não encontrado"));
	}
}
