package com.company.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.school.model.Course;
import com.company.school.service.impl.CourseServiceImpl;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	CourseServiceImpl courseService;

	@PostMapping(value = "", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> createCourse(@RequestBody Course course) {
		return new ResponseEntity<>(courseService.createCourse(course), HttpStatus.CREATED);
	}

	@GetMapping(value = "", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> getCourses() {
		return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
	}

	@GetMapping(value = "/{courseId}", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> getCourseById(@PathVariable("courseId") Long courseId) {
		return new ResponseEntity<>(courseService.getCourseById(courseId), HttpStatus.OK);
	}

	@GetMapping(value = "/{courseId}/students", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> getStudentsByCourseId(@PathVariable("courseId") Long courseId) {
		return new ResponseEntity<>(courseService.getStudentsByCourseId(courseId), HttpStatus.OK);
	}

	@GetMapping(value = "/without-students", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> getCoursesWithoutStudents() {
		return new ResponseEntity<>(courseService.getCoursesWithoutStudents(), HttpStatus.OK);
	}

	@PutMapping(value = "/{courseId}", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> updateCourse(@PathVariable("courseId") Long courseId, @RequestBody Course course) {
		return new ResponseEntity<>(courseService.updateCourse(courseId, course), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{courseId}", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> deleteCourse(@PathVariable("courseId") Long courseId) {
		courseService.deleteCourse(courseId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
