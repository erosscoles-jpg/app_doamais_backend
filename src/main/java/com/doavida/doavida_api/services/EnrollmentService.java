package com.doavida.doavida_api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.doavida.doavida_api.dto.EnrollmentRequestDTO;
import com.doavida.doavida_api.dto.EnrollmentResponseDTO;
import com.doavida.doavida_api.model.entities.Campaign;
import com.doavida.doavida_api.model.entities.Donor;
import com.doavida.doavida_api.model.entities.DonorForm;
import com.doavida.doavida_api.model.entities.Enrollment;
import com.doavida.doavida_api.model.entities.enums.EnrollmentStatus;
import com.doavida.doavida_api.model.entities.enums.FormStatus;
import com.doavida.doavida_api.repositories.CampaignRepository;
import com.doavida.doavida_api.repositories.DonorRepository;
import com.doavida.doavida_api.repositories.EnrollmentRepository;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;
    private final DonorRepository donorRepository;
    private final CampaignRepository campaignRepository;

    public EnrollmentService(EnrollmentRepository repository, DonorRepository donorRepository,
            CampaignRepository campaignRepository) {
        this.repository = repository;
        this.donorRepository = donorRepository;
        this.campaignRepository = campaignRepository;
    }

    public List<EnrollmentResponseDTO> findAll(Long donorId, Long campaignId) {
        List<Enrollment> enrollments;
        if (donorId != null && campaignId != null) {
            enrollments = repository.findByDonorIdUserAndCampaignIdCampaign(donorId, campaignId);
        } else if (donorId != null) {
            enrollments = repository.findByDonorIdUser(donorId);
        } else if (campaignId != null) {
            enrollments = repository.findByCampaignIdCampaign(campaignId);
        } else {
            enrollments = repository.findAll();
        }
        return enrollments.stream().map(EnrollmentResponseDTO::fromEntity).toList();
    }

    public EnrollmentResponseDTO findById(Long id) {
        return EnrollmentResponseDTO.fromEntity(getOrThrow(id));
    }

    public EnrollmentResponseDTO create(EnrollmentRequestDTO dto) {
        Donor donor = donorRepository.findById(dto.donorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doador não encontrado"));
        Campaign campaign = campaignRepository.findById(dto.campaignId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campanha não encontrada"));

        DonorForm form = donor.getDonorForm();
        if (form == null || form.getStatus() != FormStatus.ELIGIBLE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O doador precisa ter a ficha aprovada");
        }

        LocalDateTime date = dto.enrollmentDate();
        if (date.isBefore(campaign.getStartDate()) || date.isAfter(campaign.getEndDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data precisa estar dentro do período da campanha");
        }

        if (repository.existsByDonorIdUserAndCampaignIdCampaignAndStatus(
                dto.donorId(), dto.campaignId(), EnrollmentStatus.SCHEDULED)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O doador já tem uma inscrição agendada nesta campanha");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setDonor(donor);
        enrollment.setCampaign(campaign);
        enrollment.setEnrollmentDate(date);
        enrollment.setStatus(EnrollmentStatus.SCHEDULED);
        return EnrollmentResponseDTO.fromEntity(repository.save(enrollment));
    }

    public EnrollmentResponseDTO updateStatus(Long id, EnrollmentStatus newStatus) {
        Enrollment enrollment = getOrThrow(id);
        if (enrollment.getStatus() != EnrollmentStatus.SCHEDULED) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Só inscrições agendadas podem ser alteradas");
        }
        if (newStatus == EnrollmentStatus.SCHEDULED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A inscrição já está agendada");
        }
        enrollment.setStatus(newStatus);
        return EnrollmentResponseDTO.fromEntity(repository.save(enrollment));
    }

    private Enrollment getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscrição não encontrada"));
    }
}