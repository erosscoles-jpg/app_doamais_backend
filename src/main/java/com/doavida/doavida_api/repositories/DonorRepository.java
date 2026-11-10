package com.doavida.doavida_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doavida.doavida_api.model.entities.Donor;

public interface DonorRepository extends JpaRepository<Donor, Long> {
}
