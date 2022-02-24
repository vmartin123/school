package com.company.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.school.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	public Student findByDocument(Long document);

}
