package com.callor.student.controller;

import com.callor.student.service.StudentService;
import com.callor.student.service.impl.StudentServiceImpl;

public class StudentController {
	public static void main(String[] args) {
		StudentService stService = new StudentServiceImpl();
		stService.selectMenu();
	}
}
