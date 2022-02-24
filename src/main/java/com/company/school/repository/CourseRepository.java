package com.company.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.school.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	public Course findByName(String name);
}
