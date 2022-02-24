package com.company.school.service;

import java.util.List;

import com.company.school.model.Course;
import com.company.school.model.Student;

public interface CourseService {

	public Course createCourse(Course course);

	public List<Course> getCourses();

	public Course getCourseById(Long id);

	public List<Student> getStudentsByCourseId(Long id);

	public List<Course> getCoursesWithoutStudents();

	public Course updateCourse(Long id, Course course);

	public void deleteCourse(Long id);

}
