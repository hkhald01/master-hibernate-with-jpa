package com.in28minutes.jpa.hibernate.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.jpa.hibernate.demo.entity.Student;
import com.in28minutes.jpa.hibernate.demo.repository.StudentRepository;

@RestController
@RequestMapping("/api/v2")
public class StudentController {

  @Autowired StudentRepository studentRepository;

  @GetMapping("/students/{id}")
  public Student getStudent(@PathVariable Long id) {

    return studentRepository.findById(id);
  }
}
