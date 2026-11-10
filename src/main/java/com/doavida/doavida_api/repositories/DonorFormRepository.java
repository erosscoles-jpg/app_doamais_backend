package com.doavida.doavida_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doavida.doavida_api.model.entities.DonorForm;

public interface DonorFormRepository extends JpaRepository<DonorForm, Long> {
}
