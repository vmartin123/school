package com.company.school.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.company.school.model.Course;
import com.company.school.model.Student;
import com.company.school.repository.CourseRepository;
import com.company.school.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Override
	public Course createCourse(Course course) {
		Course courseDb = courseRepository.findByName(course.getName());

		if (courseDb != null) {
			throw new ResponseStatusException(HttpStatus.OK, "Course already exists");
		} else {
			return courseRepository.save(course);
		}
	}

	@Override
	public List<Course> getCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(Long courseId) {
		Optional<Course> course = courseRepository.findById(courseId);

		if (course.isPresent()) {
			return course.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course " + courseId + " not found");
		}
	}

	@Override
	public List<Student> getStudentsByCourseId(Long courseId) {
		Optional<Course> course = courseRepository.findById(courseId);

		if (course.isPresent()) {
			return new ArrayList<>(course.get().getStudents());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course " + courseId + " not found");
		}
	}

	@Override
	public List<Course> getCoursesWithoutStudents() {
		List<Course> courses = courseRepository.findAll();
		List<Course> coursesResult = new ArrayList<Course>();

		for (Course course : courses) {
			if (course.getStudents().size() == 0) {
				coursesResult.add(course);
			}
		}

		return coursesResult;
	}

	@Override
	public Course updateCourse(Long courseId, Course course) {
		Optional<Course> courseDb = courseRepository.findById(courseId);

		if (courseDb.isPresent()) {
			courseDb.get().setName(course.getName());

			return courseRepository.save(courseDb.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course " + courseId + " not found");
		}
	}

	@Override
	public void deleteCourse(Long courseId) {
		Optional<Course> course = courseRepository.findById(courseId);

		if (course.isPresent()) {
			courseRepository.deleteById(courseId);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course " + courseId + " not found");
		}
	}

}
