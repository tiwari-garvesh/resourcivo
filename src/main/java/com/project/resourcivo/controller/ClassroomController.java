package com.project.resourcivo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

	@GetMapping("/resp")
	public ResponseEntity<String> resp() {
		String msg = "Hello";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}
