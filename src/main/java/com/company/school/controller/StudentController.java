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
import com.company.school.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentServiceImpl studentService;

	@PostMapping(value = "", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.CREATED)
	public Student createStudent(@RequestBody Student student) {
		return studentService.createStudent(student);
	}

	@PostMapping(value = "/{studentId}/courses", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.CREATED)
	public Course assignCourse(@PathVariable("studentId") Long studentId, @RequestBody Course course) {
		return studentService.assignCourse(studentId, course);
	}

	@GetMapping(value = "", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	@GetMapping(value = "/{studentId}", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public Student getStudentById(@PathVariable("studentId") Long studentId) {
		return studentService.getStudentById(studentId);
	}

	@GetMapping(value = "/{studentId}/courses", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public List<Course> getCoursesByStudentId(@PathVariable("studentId") Long studentId) {
		return studentService.getCoursesByStudentId(studentId);
	}
	
	@GetMapping(value = "/without-courses", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public List<Student> getStudentsWithoutCourses() {
		return studentService.getStudentsWithoutCourses();
	}

	@PutMapping(value = "/{studentId}", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.OK)
	public Student updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student student) {
		return studentService.updateStudent(studentId, student);
	}

	@DeleteMapping(value = "/{studentId}", headers = "Accept=application/com.company.app-v1+json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}

}
