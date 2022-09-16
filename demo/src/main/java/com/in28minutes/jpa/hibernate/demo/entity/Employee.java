package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass

// @Entity
// @Inheritance(strategy = InheritanceType.JOINED)

// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name = "EmployeeType")
public abstract class Employee {

  @Id @GeneratedValue private Long id;

  @Column(nullable = false)
  private String name;

  public Employee(String name) {
    this.name = name;
  }
}
