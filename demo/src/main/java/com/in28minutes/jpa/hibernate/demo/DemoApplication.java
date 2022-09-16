package com.in28minutes.jpa.hibernate.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.PartTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.demo.repository.EmployeeRepository;
import com.in28minutes.jpa.hibernate.demo.repository.PassportRepository;
import com.in28minutes.jpa.hibernate.demo.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

  @Autowired CourseRepository courseRepository;
  @Autowired StudentRepository studentRepository;
  @Autowired PassportRepository passportRepository;
  @Autowired EmployeeRepository employeeRepository;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    //    Course course = courseRepository.findById(10002L);
    //    log.info("Course 10002 -> {}", course);
    //    courseRepository.save(new Course("Microservices in 100 Steps"));
    //    courseRepository.save(new Course("React in 100 Steps"));
    //    courseRepository.playWithEntityManager();
    //    studentRepository.saveStudentWithPassport();
    //    courseRepository.addReviewsForCourse();

    //    List<Review> reviews =
    //        Arrays.asList(new Review("5", "Great Hands-on Stuff"), new Review("5", "Hats off"));
    //    courseRepository.addReviewsForCourse(10003L, reviews);
    //    Student student = new Student("Jack");
    //    Course course = new Course("Microservices in 100 Steps");
    //    studentRepository.insertStudentAndCourse(student, course);
    init();
    employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
    employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
    //    log.info("all employees -> {}", employeeRepository.getAllEmployees());
    log.info("all full time employees -> {}", employeeRepository.getAllFullTimeEmployees());
    log.info("all part time employees -> {}", employeeRepository.getAllPartTimeEmployees());
  }

  @Transactional
  public void init() {

    Course course1 = new Course("JPA in 50 Steps", LocalDateTime.now(), LocalDateTime.now());
    Course course2 = new Course("Spring in 50 Steps", LocalDateTime.now(), LocalDateTime.now());
    Course course3 =
        new Course("Spring Boot in 100 Step", LocalDateTime.now(), LocalDateTime.now());
    List<Course> courses = Arrays.asList(course1, course2, course3);

    Review review1 = new Review("5", "Great Course");
    Review review2 = new Review("4", "Wonderful Course");
    Review review3 = new Review("5", "Awesome Course");

    course1.addReview(review1);
    review1.setCourse(course1);

    course1.addReview(review2);
    review2.setCourse(course1);

    course3.addReview(review3);
    review3.setCourse(course1);

    Student student1 = new Student("Ranga");
    Student student2 = new Student("Adam");
    Student student3 = new Student("Jane");

    List<Student> students = Arrays.asList(student1, student2, student3);

    Passport passport1 = new Passport("E123456");
    Passport passport2 = new Passport("N123457");
    Passport passport3 = new Passport("L123890");
    List<Passport> passports = Arrays.asList(passport1, passport2, passport3);

    for (Passport passport : passports) {
      passportRepository.save(passport);
    }

    for (Course course : courses) {
      courseRepository.save(course);
    }

    courseRepository.addReviewPerCourse(course1, review1);
    courseRepository.addReviewPerCourse(course1, review2);
    courseRepository.addReviewPerCourse(course3, review3);

    student1.setPassport(passport1);
    passport1.setStudent(student1);
    student2.setPassport(passport2);
    passport2.setStudent(student2);
    student3.setPassport(passport3);
    passport3.setStudent(student3);

    for (Student student : students) {
      studentRepository.save(student);
    }

    course1.addStudent(student1);
    student1.addCourse(course1);

    courseRepository.save(course1);
    studentRepository.save(student1);
  }
}
