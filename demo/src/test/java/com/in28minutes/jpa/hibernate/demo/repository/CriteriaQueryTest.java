package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CriteriaQueryTest {

  // section - 11
  @Autowired EntityManager em;

  @Test
  public void jpqs_basic() {

    // select c from Course c

    // 1. Use Criteria builder to create a Criteria Query returning the expected result
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
    // 2. Define result object
    Root<Course> courseRoot = criteriaQuery.from(Course.class);
    // 3. Build the TypedQuery using the entity manager and criteria query

    TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
    List<Course> resultList = query.getResultList();
    log.info("Typed Query {}", resultList);
  }

  @Test
  public void jpqs_basic_wehre_clause() {

    // select c from Course c where name like '%100 Steps%

    // 1. Use Criteria builder to create a Criteria Query returning the expected result
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
    // 2. Define result object
    Root<Course> courseRoot = criteriaQuery.from(Course.class);
    // 3. Define Predicates etc using Criteria Builder
    Predicate like100Steps = criteriaBuilder.like(courseRoot.get("name"), "%100 Steps%");
    // 4. Add Predicates etc to the Criteria Query
    criteriaQuery.where(like100Steps);
    // 5. Build the TypedQuery using the entity manager and criteria query

    TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
    List<Course> resultList = query.getResultList();
    log.info("Typed Query where clause {}", resultList);
  }

  @Test
  public void jpqs_basic_without_students() {

    // select c,s from Course c where c.students is empty

    // 1. Use Criteria builder to create a Criteria Query returning the expected result
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
    // 2. Define result object
    Root<Course> courseRoot = criteriaQuery.from(Course.class);
    // 3. Define Predicates etc using Criteria Builder
    Predicate studentsIsEmpty = criteriaBuilder.isEmpty(courseRoot.get("students"));
    // 4. Add Predicates etc to the Criteria Query
    criteriaQuery.where(studentsIsEmpty);
    // 5. Build the TypedQuery using the entity manager and criteria query

    TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
    List<Course> resultList = query.getResultList();
    log.info("Typed Query where students is empty {}", resultList);
  }

  @Test
  public void jpqs_join() {

    // select c,s from Course c join c.students s

    // 1. Use Criteria builder to create a Criteria Query returning the expected result
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
    // 2. Define result object
    Root<Course> courseRoot = criteriaQuery.from(Course.class);
    // 3. Define Predicates etc using Criteria Builder
    Join<Object, Object> join = courseRoot.join("students");
    // 4. Build the TypedQuery using the entity manager and criteria query
    TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
    List<Course> resultList = query.getResultList();
    log.info("Typed Query join with students {}", resultList);
  }

  @Test
  public void jpqs_left_join() {

    // select c,s from Course c left join c.students s

    // 1. Use Criteria builder to create a Criteria Query returning the expected result
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
    // 2. Define result object
    Root<Course> courseRoot = criteriaQuery.from(Course.class);
    // 3. Define Predicates etc using Criteria Builder
    Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
    // 4. Build the TypedQuery using the entity manager and criteria query
    TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
    List<Course> resultList = query.getResultList();
    log.info("Typed Query left join with students{}", resultList);
  }
}
