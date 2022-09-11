package com.in28minutes.jpa.hibernate.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

  @Autowired CourseRepository courseRepository;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Course course = courseRepository.findById(10002L);
    log.info("Course 10002 -> {}", course);
    //    courseRepository.save(new Course("Microservices in 100 Steps"));
    //    courseRepository.save(new Course("React in 100 Steps"));
    // courseRepository.playWithEntityManager();
  }
}
