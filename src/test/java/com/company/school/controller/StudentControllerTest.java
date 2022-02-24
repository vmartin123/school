package com.company.school.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.company.school.model.Course;
import com.company.school.model.Student;
import com.company.school.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	StudentRepository studentRepository;
	
	@Test
	public void createStudent() throws Exception {
		Student studentReq = new Student();
		studentReq.setDocument(95573928);
		studentReq.setName("victor");
		studentReq.setLastName("martin");
		
		Student studentRes = new Student();
		studentRes.setId(1l);
		studentRes.setDocument(95573928);
		studentRes.setName("victor");
		studentRes.setLastName("martin");
		
		Mockito.when(studentRepository.findByDocument(any(Long.class))).thenReturn(null);
		Mockito.when(studentRepository.save(any(Student.class))).thenReturn(studentRes);

		mockMvc.perform(post("/students")
				.contentType("application/com.company.app-v1+json")
		.content(objectMapper.writeValueAsString(studentReq)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.document").value(95573928))
        .andExpect(jsonPath("$.name").value("victor"))
        .andExpect(jsonPath("$.lastName").value("martin"));
	}

	@Test
	public void getStudents() throws Exception {
		Course course = new Course();
		course.setId(2l);
		course.setName("programming");

		HashSet<Course> courses = new HashSet<>();
		courses.add(course);

		Student student = new Student();
		student.setDocument(96773541);
		student.setName("pepito");
		student.setLastName("quintero");
		student.setCourses(courses);

		List<Student> students = new ArrayList<>();
		students.add(student);

		Mockito.when(studentRepository.findAll()).thenReturn(students);

		mockMvc.perform(get("/students"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.[0].document").value(96773541))
        .andExpect(jsonPath("$.[0].name").value("pepito"))
        .andExpect(jsonPath("$.[0].lastName").value("quintero"))
        .andExpect(jsonPath("$.[0].courses.[0].id").value(2))
        .andExpect(jsonPath("$.[0].courses.[0].name").value("programming"));
	}
	
}
