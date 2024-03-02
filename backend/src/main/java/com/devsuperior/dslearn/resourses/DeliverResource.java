package com.devsuperior.dslearn.resourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslearn.dto.DeliverRevisionDTO;
import com.devsuperior.dslearn.services.DeliverService;


@RestController
@RequestMapping(value = "/deliveries")
public class DeliverResource {
	
	@Autowired
	private DeliverService service;
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> saveRevision(@PathVariable Long id, @RequestBody DeliverRevisionDTO dto) {
		service.saveRevision(id, dto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
