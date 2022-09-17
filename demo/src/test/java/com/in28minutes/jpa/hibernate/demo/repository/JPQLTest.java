package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

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
  public void jpqlQueryCoursesByTypeWhereClause() {
    List<Course> resultList =
        em.createNamedQuery("query_get_100_Steps_courses", Course.class).getResultList();
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
    Query query = em.createNativeQuery("Update COURSE set last_updated_date = CURRENT_TIMESTAMP()");
    // we can't find the number of rows updated with jpa
    int numberOfRowsUpdated = query.executeUpdate();
    log.info("Native Query Number Of Rows Updated-> {}", numberOfRowsUpdated);
  }
  // section-10

  @Test
  public void jpqlQueryCoursesWithoutStudents() {
    List<Course> resultList =
        em.createQuery("select c from Course c where c.students is empty ", Course.class)
            .getResultList();
    log.info("Select c from Course c where students is empty -> {}", resultList);
  }

  @Test
  public void jpqlQueryCoursesWith_at_least_2_Students() {
    List<Course> resultList =
        em.createQuery("select c from Course c where size(c.students) >=2 ", Course.class)
            .getResultList();
    log.info("Select c from Course c where students >-2 -> {}", resultList);
  }

  @Test
  public void jpqlQueryCourses_order_by_desc_Students() {
    List<Course> resultList =
        em.createQuery("select c from Course c order by size(c.students) desc", Course.class)
            .getResultList();
    log.info("Select c from Course c orderby students size -> {}", resultList);
  }

  @Test
  public void jpql_query_students_with_passport_in_a_certain_pattern() {
    List<Student> resultList =
        em.createQuery(
                "select s from Student s where s.passport.number like '%1234%' ", Student.class)
            .getResultList();
    log.info("select s from Student s where s.passport.number like '%1234' -> {}", resultList);
  }
  // like
  // between 100 and 1000
  // is null
  // upper, lower, trim, length

  // join       => select c,s from Course c JOIN c.students s
  // left join  => select c,s from Course c LEFT JOIN c.students s
  // cross join => select c,s from Course c , Student s
  @Test
  public void join() {
    Query query = em.createQuery("select c, s from Course c JOIN c.students s");
    List<Object[]> resultList = query.getResultList();
    log.info("join result -> {} ", resultList.size());
    for (Object[] result : resultList) {
      log.info("{} {}", result[0], result[1]);
    }
  }

  @Test
  public void left_join() {
    Query query = em.createQuery("select c, s from Course c LEFT JOIN c.students s");
    List<Object[]> resultList = query.getResultList();
    log.info("join result -> {} ", resultList.size());
    for (Object[] result : resultList) {
      log.info("{} {}", result[0], result[1]);
    }
  }

  @Test
  public void cross_join() {
    Query query = em.createQuery("select c, s from Course c, Student s");
    List<Object[]> resultList = query.getResultList();
    log.info("join result -> {} ", resultList.size());
    for (Object[] result : resultList) {
      log.info("{} {}", result[0], result[1]);
    }
  }
}
