package com.devsuperior.dslearn.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.devsuperior.dslearn.entities.primaryKeys.EnrollmentPk;

@Entity
@Table(name = "tb_enrollment")
public class Enrollment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private EnrollmentPk id = new EnrollmentPk(); //Chave Primaria composta como tipo customizado
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant enrollMoment;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant refundMoment;
	private boolean available;
	private boolean onlyUpdate;
	
	public Enrollment() {
	}

	public Enrollment(User user, Offer offer, Instant enrollMoment, Instant refundMoment, boolean available,
			boolean onlyUpdate) {
		id.setUser(user);
		id.setOffer(offer);
		this.enrollMoment = enrollMoment;
		this.refundMoment = refundMoment;
		this.available = available;
		this.onlyUpdate = onlyUpdate;
	}
	
	//// customização da chave primaria composta
	public User getStudent() {
		return id.getUser();
	}

	public void setStudent(User user) {
		id.setUser(user);
	}
	
	public Offer getOffer() {
		return id.getOffer();
	}

	public void setOffer(Offer offer) {
		id.setOffer(offer);
	} /////

	public Instant getEnrollMoment() {
		return enrollMoment;
	}

	public void setEnrollMoment(Instant enrollMoment) {
		this.enrollMoment = enrollMoment;
	}

	public Instant getRefundMoment() {
		return refundMoment;
	}

	public void setRefundMoment(Instant refundMoment) {
		this.refundMoment = refundMoment;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isOnlyUpdate() {
		return onlyUpdate;
	}

	public void setOnlyUpdate(boolean onlyUpdate) {
		this.onlyUpdate = onlyUpdate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrollment other = (Enrollment) obj;
		return Objects.equals(id, other.id);
	}
		
}
