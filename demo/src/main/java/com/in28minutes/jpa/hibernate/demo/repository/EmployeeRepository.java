package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Employee;
import com.in28minutes.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {

  @Autowired EntityManager em;

  public void insert(Employee employee) {
    em.persist(employee);
  }

  public Employee findById(Long id) {
    return em.find(Employee.class, id);
  }

  //  public List<Employee> getAllEmployees() {
  //    return em.createQuery("select e from Employee e", Employee.class).getResultList();
  //  }

  public List<PartTimeEmployee> getAllPartTimeEmployees() {
    return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class)
        .getResultList();
  }

  public List<FullTimeEmployee> getAllFullTimeEmployees() {
    return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class)
        .getResultList();
  }
}
