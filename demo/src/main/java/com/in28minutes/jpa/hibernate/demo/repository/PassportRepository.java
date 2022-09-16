package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Passport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
public class PassportRepository {

  @Autowired EntityManager em;

  public Passport findById(Long id) {
    return em.find(Passport.class, id);
  }

  public Passport save(Passport passport) {
    if (passport.getId() == null) {
      em.persist(passport);
    } else {
      em.merge(passport);
    }
    return passport;
  }
}
