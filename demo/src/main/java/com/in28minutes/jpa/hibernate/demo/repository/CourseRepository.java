package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import com.in28minutes.jpa.hibernate.demo.entity.ReviewRating;

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

  public void addReviewsForCourse() {
    // get the course 10003
    Course course = findById(10003L);
    log.info("reviews -> {}", course.getReviews());
    // add 2 reviews
    Review review1 = new Review(ReviewRating.FIVE, "Great Hands-on Stuff");
    Review review2 = new Review(ReviewRating.FIVE, "Hats off");

    course.addReview(review1);
    review1.setCourse(course);
    course.addReview(review2);
    review2.setCourse(course);

    // save it to database

    em.persist(review1);
    em.persist(review2);
  }

  public void addReviewsForCourse(Long id, List<Review> reviews) {

    Course course = findById(id);

    for (Review review : reviews) {
      course.addReview(review);
      review.setCourse(course);
      em.persist(review);
    }
  }

  public void addReviewPerCourse(Course course, Review review) {
    course.getReviews().add(review);
    course.setReviews(course.getReviews());
    review.setCourse(course);
    em.persist(review);
  }
}
