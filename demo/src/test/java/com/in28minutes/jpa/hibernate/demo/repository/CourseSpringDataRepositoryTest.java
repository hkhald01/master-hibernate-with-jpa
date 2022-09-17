package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

// section - 13

@Slf4j
@SpringBootTest
public class CourseSpringDataRepositoryTest {

  @Autowired CourseSpringDataRepository repository;

  @Test
  public void findById_CoursePresent() {

    Optional<Course> courseOptional = repository.findById(4L);
    assertTrue(courseOptional.isPresent());
  }

  @Test
  public void findById_CourseNotPresent() {

    Optional<Course> courseOptional = repository.findById(1L);
    assertFalse(courseOptional.isPresent());
  }

  @Test
  public void addCourse() {
    Course course = new Course("Microservices in 100 Steps");
    repository.save(course);
    course.setName(course.getName() + " - Updated");
    repository.save(course);
    log.info("Courses -> {}", repository.findAll());
    log.info("Count -> {}", repository.count());
  }

  @Test
  public void sortCourses() {
    Sort descending = Sort.by("name").descending();
    log.info("Courses -> {}", repository.findAll(descending));
    log.info("Count -> {}", repository.count());
  }

  @Test
  public void PaginateCourses() {
    PageRequest pageRequest = PageRequest.of(0, 2);
    Page<Course> firstPage = repository.findAll(pageRequest);
    log.info("Courses -> {}", firstPage.getContent());

    Pageable secondPageable = firstPage.nextPageable();
    Page<Course> secondPage = repository.findAll(secondPageable);
    log.info("Courses -> {}", secondPage.getContent());
  }

  @Test
  public void findByName() {
    log.info("find Courses by name -> {}", repository.findByName("JPA in 50 Steps"));
  }
}
