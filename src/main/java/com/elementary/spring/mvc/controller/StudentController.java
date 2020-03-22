package com.elementary.spring.mvc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elementary.spring.mvc.model.Student;

@RestController	
@RequestMapping("api/v1/students")
public class StudentController {
	private static final List<Student> STUDENSS = Arrays.asList(new Student());
	
	
	
	
}
