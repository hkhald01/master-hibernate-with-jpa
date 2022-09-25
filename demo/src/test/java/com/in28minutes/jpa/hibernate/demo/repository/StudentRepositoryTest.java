package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.demo.entity.Address;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class StudentRepositoryTest {

  @Autowired StudentRepository studentRepository;
  @Autowired EntityManager em;

  // Session & Session Factory
  // EntityManager & Persistence Context
  // Transaction

  @Test
  // @Transactional // will create Persistence Context
  public void someTest() {

    studentRepository.someDummyOperaton();
  }

  @Test
  @Transactional
  public void retrieveStudentAndPassport() {

    Student student = em.find(Student.class, 20001L);
    log.info("Student -> {}", student);
    log.info("Passport info -> {}", student.getPassport());
  }

  @Test
  @Transactional
  public void setAddressStudent() {

    Student student = em.find(Student.class, 10L);
    student.setAddress(new Address("123 street", "apt 1", "warren"));
    em.persist(student);
    log.info("Student -> {}", student);
    log.info("Passport info -> {}", student.getAddress());
  }

  @Test
  @Transactional
  public void retrievePassportAndStudent() {

    Student student = em.find(Student.class, 20001L);
    log.info("student -> {}", student);
    log.info("courses -> {}", student.getCourses());

    Passport passport = em.find(Passport.class, 40001L);
    log.info("Passport -> {}", passport);
    log.info("Student info -> {}", passport.getStudent());
  }
}
