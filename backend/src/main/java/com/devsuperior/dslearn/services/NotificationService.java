package com.devsuperior.dslearn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dslearn.dto.NotificationDTO;
import com.devsuperior.dslearn.entities.Notification;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.repositories.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository repository;
	
	@Autowired
	private AuthService authService;
	
	public Page<NotificationDTO> findNotificationByCurrrentUser(boolean unreadOnly, Pageable pageable) {
		//verifica usuario logado
		User user = authService.authenticatedUser();
		Page<Notification> page = repository.find(user, unreadOnly, pageable);
		return page.map(x -> new NotificationDTO(x));
	}

}
