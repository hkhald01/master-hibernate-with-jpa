package com.in28minutes.database.databasedemo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Person;

@Repository
@Transactional
public class PersonJpaRepository {

  @PersistenceContext EntityManager em;

  public List<Person> findAll() {
    TypedQuery<Person> namedQuery = em.createNamedQuery("find_all_persons", Person.class);
    return namedQuery.getResultList();
  }

  public Person findById(Long id) {
    return em.find(Person.class, id);
  }

  public void deleteById(Long id) {
    Person person = em.find(Person.class, id);
    em.remove(person);
  }

  public Person saveOrUpdate(Person person) {
    return em.merge(person);
  }
}
