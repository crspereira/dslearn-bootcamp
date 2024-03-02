package com.devsuperior.dslearn.resourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslearn.dto.NotificationDTO;
import com.devsuperior.dslearn.services.NotificationService;

@RestController
@RequestMapping(value = "/notifications") /* rota dos endpoints */
public class NotificationResource {
	
	//dependencias
	@Autowired
	private NotificationService service;
	
	//com paginação
	@GetMapping
	public ResponseEntity<Page<NotificationDTO>> findNotificationByCurrrentUser(
							@PageableDefault(page= 0, size= 12)
							@RequestParam Boolean unreadOnly, Pageable pageable) {
		Page<NotificationDTO> page = service.findNotificationByCurrrentUser(unreadOnly, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}	

}
