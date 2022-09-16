package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j
public class StudentRepository {

  @Autowired EntityManager em;

  public Student findById(Long id) {
    return em.find(Student.class, id);
  }

  public Student save(Student student) {
    if (student.getId() == null) {
      em.persist(student);
    } else {
      em.merge(student);
    }
    return student;
  }

  public void deleteById(Long id) {
    Student student = em.find(Student.class, id);
    em.remove(student);
  }

  public void saveStudentWithPassport() {
    Passport passport = new Passport("Z123456");
    em.persist(passport);
    Student student = new Student("Mike");
    student.setPassport(passport);
    em.persist(student);
  }

  public void someDummyOperaton() {
    // Database Operation 1 - retrieve student
    Student student = em.find(Student.class, 20001L);
    // Persistence Context (student)

    // Database Operation 2 - retrieve passport
    Passport passport = student.getPassport();
    // Persistence Context (student, passport)

    //  Database Operation 3 - update passport
    passport.setNumber("E123457");
    // Persistence Context (student, passport ++)

    // Database Operation 4 - update student
    student.setName("Ranga - updated");
    // Persistence Context (student ++, passport ++)
  }

  public void insertStudentAndCourse() {
    Student student = new Student("Jack");
    Course course = new Course("Microservices in 100 Steps");

    em.persist(student);
    em.persist(course);

    student.addCourse(course);
    course.addStudent(student);

    em.persist(student);
  }

  public void insertStudentAndCourse(Student student, Course course) {
    student.addCourse(course);
    course.addStudent(student);

    if (course.getId() == null) {
      em.persist(course);
    } else {
      em.merge(course);
    }
    em.persist(course);
    if (student.getId() == null) {
      em.persist(student);
    } else {
      em.merge(student);
    }
  }
}
