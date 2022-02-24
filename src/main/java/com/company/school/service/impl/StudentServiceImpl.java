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
import com.company.school.repository.StudentRepository;
import com.company.school.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private final int MAXIMUM_COURSES_PER_STUDENT = 5;
	private final int MAXIMUM_STUDENTS_PER_COURSE = 50;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Override
	public Student createStudent(Student student) {
		Student studentDb = studentRepository.findByDocument(student.getDocument());

		if (studentDb != null) {
			throw new ResponseStatusException(HttpStatus.OK, "Student already exists");
		} else {
			return studentRepository.save(student);
		}

	}

	@Override
	public Course assignCourse(Long studentId, Course course) {
		Optional<Student> studentDb = studentRepository.findById(studentId);
		Optional<Course> courseDb = courseRepository.findById(course.getId());

		if (!studentDb.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student " + studentId + " not found");
		} else if (!courseDb.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course " + course.getId() + " not found");
		}

		if (studentDb.get().getCourses().size() >= MAXIMUM_COURSES_PER_STUDENT) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Limit of " + MAXIMUM_COURSES_PER_STUDENT + " courses per student reached");
		}

		if (courseDb.get().getStudents().size() >= MAXIMUM_STUDENTS_PER_COURSE) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Limit of " + MAXIMUM_STUDENTS_PER_COURSE + " students max per course reached");
		}

		studentDb.get().getCourses().add(courseDb.get());
		studentRepository.save(studentDb.get());

		return courseDb.get();
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);

		if (student.isPresent()) {
			return student.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student " + studentId + " not found");
		}
	}

	@Override
	public List<Course> getCoursesByStudentId(Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);

		if (student.isPresent()) {
			return new ArrayList<>(student.get().getCourses());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student " + studentId + " not found");
		}
	}

	@Override
	public List<Student> getStudentsWithoutCourses() {
		List<Student> students = studentRepository.findAll();
		List<Student> studentsResult = new ArrayList<Student>();

		for (Student student : students) {
			if (student.getCourses().size() == 0) {
				studentsResult.add(student);
			}
		}

		return studentsResult;
	}

	@Override
	public Student updateStudent(Long studentId, Student student) {
		Optional<Student> studentDb = studentRepository.findById(studentId);

		if (studentDb.isPresent()) {
			studentDb.get().setDocument(student.getDocument());
			studentDb.get().setName(student.getName());
			studentDb.get().setLastName(student.getLastName());

			return studentRepository.save(studentDb.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student " + studentId + " not found");
		}
	}

	@Override
	public void deleteStudent(Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);

		if (student.isPresent()) {
			studentRepository.deleteById(studentId);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student " + studentId + " not found");
		}
	}

}
