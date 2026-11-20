package com.doavida.doavida_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doavida.doavida_api.model.entities.Enrollment;
import com.doavida.doavida_api.model.entities.enums.EnrollmentStatus;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	
    List<Enrollment> findByDonorIdUser(Long donorId);

    List<Enrollment> findByCampaignIdCampaign(Long campaignId);

    List<Enrollment> findByDonorIdUserAndCampaignIdCampaign(Long donorId, Long campaignId);

    boolean existsByDonorIdUserAndCampaignIdCampaignAndStatus(Long donorId, Long campaignId, EnrollmentStatus status);
}
