package com.devsuperior.dslearn.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dslearn.entities.Notification;
import com.devsuperior.dslearn.entities.User;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	//jpql
	@Query("SELECT obj FROM Notification obj WHERE "
			+ "(obj.user = :user) AND "
			+ "(:unreadOnly = false OR obj.read = false) " //uma expressao é true, se toda expressao for true 
			+ "ORDER BY obj.moment DESC")
	Page<Notification> find(User user, boolean unreadOnly, Pageable pageable);
	
}
