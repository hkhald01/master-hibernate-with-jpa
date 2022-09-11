package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

  @Autowired CourseRepository courseRepository;

  @Test
  public void findCourseById_basic() {
    Course course = courseRepository.findById(10001L);
    assertEquals("JPA in 50 Steps", course.getName());
  }

  @Test
  @DirtiesContext
  public void deleteCourseById_basic() {
    courseRepository.deleteById(10002L);
    assertNull(courseRepository.findById(10002L));
  }

  @Test
  @DirtiesContext
  public void saveCourse_basic() {
    Course course = courseRepository.findById(10001L);
    assertEquals("JPA in 50 Steps", course.getName());
    course.setName("JPA in 50 Steps - Updated");
    courseRepository.save(course);
    Course updatedCourse = courseRepository.findById(10001L);
    assertEquals("JPA in 50 Steps - Updated", updatedCourse.getName());
  }

  @Test
  @DirtiesContext
  public void playWithEntityManager() {
    courseRepository.playWithEntityManager();
    Course course1 = courseRepository.findById(3L);
    assertEquals("Web Services in 100 Steps - Updated", course1.getName());
  }
}
