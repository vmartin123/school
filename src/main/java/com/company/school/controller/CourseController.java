package com.company.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.school.model.Course;
import com.company.school.model.Student;
import com.company.school.service.impl.CourseServiceImpl;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	CourseServiceImpl courseService;

	@PostMapping(value = "", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.CREATED)
	public Course createCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
	}

	@GetMapping(value = "", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public List<Course> getCourses() {
		return courseService.getCourses();
	}

	@GetMapping(value = "/{courseId}", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public Course getCourseById(@PathVariable("courseId") Long courseId) {
		return courseService.getCourseById(courseId);
	}
	
	@GetMapping(value = "/{courseId}/students", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public List<Student> getStudentsByCourseId(@PathVariable("courseId") Long courseId) {
		return courseService.getStudentsByCourseId(courseId);
	}
	
	@GetMapping(value = "/without-students", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public List<Course> getCoursesWithoutStudents() {
		return courseService.getCoursesWithoutStudents();
	}

	@PutMapping(value = "/{courseId}", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public Course updateCourse(@PathVariable("courseId") Long courseId, @RequestBody Course course) {
		return courseService.updateCourse(courseId, course);
	}

	@DeleteMapping(value = "/{courseId}", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCourse(@PathVariable("courseId") Long courseId) {
		courseService.deleteCourse(courseId);
	}

}
