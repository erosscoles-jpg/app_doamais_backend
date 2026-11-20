package com.doavida.doavida_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doavida.doavida_api.model.entities.Donor;
import com.doavida.doavida_api.model.entities.enums.FormStatus;

public interface DonorRepository extends JpaRepository<Donor, Long> {
	
	boolean existsByEmail(String email);
	
    List<Donor> findByDonorFormIsNotNull();

    List<Donor> findByDonorFormStatus(FormStatus status);
}
