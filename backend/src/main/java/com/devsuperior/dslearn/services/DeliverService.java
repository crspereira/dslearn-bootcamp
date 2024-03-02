package com.devsuperior.dslearn.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslearn.dto.DeliverRevisionDTO;
import com.devsuperior.dslearn.entities.Deliver;
import com.devsuperior.dslearn.repositories.DeliverRepository;
import com.devsuperior.dslearn.services.execeptions.ResourceNotFoundException;

@Service
public class DeliverService {
	
	@Autowired
	private DeliverRepository repository;
	
	@PreAuthorize(value = "hasAnyRole('ADMIN', 'INSTRUCTOR')")
	@Transactional
	public void saveRevision(Long id, DeliverRevisionDTO dto) {
		try {
			Deliver deliver = repository.getReferenceById(id);
			deliver.setStatus(dto.getStatus());
			deliver.setFeedback(dto.getFeedback());
			deliver.setCorrectCount(dto.getCorrectCount());
			
			repository.save(deliver);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id Not Found! " + id);
		}
	}

}
