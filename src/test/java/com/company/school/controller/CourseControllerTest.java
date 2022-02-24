package com.company.school.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.company.school.model.Course;
import com.company.school.repository.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	CourseRepository courseRepository;
	
	@Test
	public void createCourse() throws Exception {
		Course courseReq = new Course();
		courseReq.setName("geology");
		
		Course courseRes = new Course();
		courseRes.setId(1l);
		courseRes.setName("geology");
		
		Mockito.when(courseRepository.findByName(any(String.class))).thenReturn(null);
		Mockito.when(courseRepository.save(any(Course.class))).thenReturn(courseRes);
		
		mockMvc.perform(post("/courses")
				.contentType("application/com.company.app-v1+json")
		.content(objectMapper.writeValueAsString(courseReq)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("geology"));
	}
	
	@Test
	public void getCourseById() throws Exception {
		Optional<Course> courseRes = Optional.of(new Course());
		courseRes.get().setId(2l);
		courseRes.get().setName("programming");

		Mockito.when(courseRepository.findById(any(Long.class))).thenReturn(courseRes);

		mockMvc.perform(get("/courses/{courseId}", 2))
		.andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(2l))
        .andExpect(jsonPath("$.name").value("programming"));
	}

}
