package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JPQLTest {

  @Autowired EntityManager em;

  @Test
  public void jpqlQueryCourses() {
    List resultList = em.createQuery("Select c from Course c").getResultList();
    log.info("Select c from Course c -> {}", resultList);
  }

  @Test
  public void jpqlQueryCoursesByType() {
    List<Course> resultList =
        em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
    log.info("Named Query Select c from Course c -> {}", resultList);
  }

  @Test
  public void jpqlQueryCoursesByTypeWhereCLause() {
    List<Course> resultList =
        em.createQuery("Select c from Course c where name like '%100 Steps'", Course.class)
            .getResultList();
    log.info("Select c from Course c where name like '%100 Steps' -> {}", resultList);
  }

  @Test
  public void nativeQueryCoursesByType() {
    Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
    @SuppressWarnings("unchecked")
    List<Course> resultList = query.getResultList();
    log.info("Native Query Select c from Course c -> {}", resultList);
  }

  @Test
  public void nativeQueryCoursesByTypeWithParam() {
    Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id=?", Course.class);
    query.setParameter(1, 10001L);
    @SuppressWarnings("unchecked")
    List<Course> resultList = query.getResultList();
    log.info("Native Query With Param Select c from Course c -> {}", resultList);
  }

  @Test
  public void nativeQueryCoursesByTypeWithNamedParam() {
    Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id= :id", Course.class);
    query.setParameter("id", 10001L);
    @SuppressWarnings("unchecked")
    List<Course> resultList = query.getResultList();
    log.info("Native Query With Param Select c from Course c -> {}", resultList);
  }

  @Test
  @Transactional
  public void nativeQueryCoursesByTypeUpdate() {
    Query query = em.createNativeQuery("Update COURSE set last_updated_date = current_date()");
    // we can't find the number of rows updated with jpa
    int numberOfRowsUpdated = query.executeUpdate();
    log.info("Native Query Number Of Rows Updated-> {}", numberOfRowsUpdated);
  }
}
