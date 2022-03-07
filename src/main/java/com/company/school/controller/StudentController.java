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
import com.company.school.model.Student;
import com.company.school.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentServiceImpl studentService;

	@PostMapping(value = "", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
	}

	@PostMapping(value = "/{studentId}/courses", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> assignCourse(@PathVariable("studentId") Long studentId, @RequestBody Course course) {
		return new ResponseEntity<>(studentService.assignCourse(studentId, course), HttpStatus.CREATED);
	}

	@GetMapping(value = "", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> getStudents() {
		return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
	}

	@GetMapping(value = "/{studentId}", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> getStudentById(@PathVariable("studentId") Long studentId) {
		return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
	}

	@GetMapping(value = "/{studentId}/courses", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> getCoursesByStudentId(@PathVariable("studentId") Long studentId) {
		return new ResponseEntity<>(studentService.getCoursesByStudentId(studentId), HttpStatus.OK);
	}

	@GetMapping(value = "/without-courses", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> getStudentsWithoutCourses() {
		return new ResponseEntity<>(studentService.getStudentsWithoutCourses(), HttpStatus.OK);
	}

	@PutMapping(value = "/{studentId}", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> updateStudent(@PathVariable("studentId") Long studentId,
			@RequestBody Student student) {
		return new ResponseEntity<>(studentService.updateStudent(studentId, student), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{studentId}", headers = "Accept=application/com.company.app-v1+json")
	public ResponseEntity<Object> deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
