package com.doavida.doavida_api.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.doavida.doavida_api.dto.CampaignDTO;
import com.doavida.doavida_api.model.entities.Campaign;
import com.doavida.doavida_api.repositories.CampaignRepository;

@Service
public class CampaignService {

	private final CampaignRepository repository;
	
	public CampaignService(CampaignRepository repository) {
		this.repository = repository;
	}
	
	public List<CampaignDTO> findAll() {
		return repository.findAll().stream().map(CampaignDTO::fromEntity).toList();
	}
	
	public CampaignDTO findById(Long id) {
		return CampaignDTO.fromEntity(getOrThrow(id));
	}
	
	public CampaignDTO create(CampaignDTO dto) {
		validateDates(dto);
		Campaign campaign = new Campaign(null, dto.campaignName(), dto.location(), dto.startDate(), dto.endDate());
		return CampaignDTO.fromEntity(repository.save(campaign));
	}
	
	public CampaignDTO update(Long id, CampaignDTO dto) {
		validateDates(dto);
		Campaign campaign = getOrThrow(id);
		campaign.getCampaignName();
		campaign.getLocation();
		campaign.getStartDate();
		campaign.getEndDate();
		return CampaignDTO.fromEntity(repository.save(campaign));
	}
	
	public void delete(Long id) {
		getOrThrow(id);
		repository.deleteById(id);
	}
	
	private Campaign getOrThrow(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campanha não encontrada"));
	}
	
	private void validateDates(CampaignDTO dto) {
		if (dto.endDate().isBefore(dto.startDate())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data de fim não pode ser anterior à de início");
		}
	}
}
