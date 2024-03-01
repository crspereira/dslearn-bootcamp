package com.devsuperior.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dslearn.entities.Enrollment;
import com.devsuperior.dslearn.entities.primaryKeys.EnrollmentPk;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentPk> {

}
