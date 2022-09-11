package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
public class CourseRepository {

  @Autowired EntityManager em;

  public Course findById(Long id) {
    return em.find(Course.class, id);
  }

  public Course save(Course course) {
    if (course.getId() == null) {
      em.persist(course);
    } else {
      em.merge(course);
    }
    return course;
  }

  public void deleteById(Long id) {
    Course course = em.find(Course.class, id);
    em.remove(course);
  }

  public void playWithEntityManager() {
    Course course1 = new Course("Web Services in 100 Steps");
    em.persist(course1);

    Course course2 = new Course("Angular in 100 Steps");
    em.persist(course2);

    em.flush();

    course1.setName("Web Services in 100 Steps - Updated");
    // em.detach(course2);
    course2.setName("Angular in 100 Steps - Updated");
    log.info("course 1 -> {}", course1);

    em.refresh(course1);
    log.info("course 1 refreshed -> {}", course1);

    em.flush();
  }
}
