package com.company.school.service;

import java.util.List;

import com.company.school.model.Course;
import com.company.school.model.Student;

public interface StudentService {

	public Student createStudent(Student student);

	public Course assignCourse(Long studentId, Course course);

	public List<Student> getStudents();

	public Student getStudentById(Long id);

	public List<Course> getCoursesByStudentId(Long studentId);

	public List<Student> getStudentsWithoutCourses();

	public Student updateStudent(Long id, Student student);

	public void deleteStudent(Long id);

}
