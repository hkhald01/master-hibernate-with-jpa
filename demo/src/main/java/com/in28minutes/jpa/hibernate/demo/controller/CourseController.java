package com.in28minutes.jpa.hibernate.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;

@RestController
@RequestMapping("/api/v2")
public class CourseController {

  @Autowired CourseRepository courseRepository;

  @GetMapping("/courses/{id}")
  public Course getCourse(@PathVariable Long id) {
    return courseRepository.findById(id);
  }
}
