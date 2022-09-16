package com.in28minutes.jpa.hibernate.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullTimeEmployee extends Employee {

  private BigDecimal salary;

  public FullTimeEmployee(String name, BigDecimal salary) {
    super(name);
    this.salary = salary;
  }
}
