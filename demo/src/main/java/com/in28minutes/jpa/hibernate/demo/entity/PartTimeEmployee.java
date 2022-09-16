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
public class PartTimeEmployee extends Employee {

  //  protected PartTimeEmployee() {}

  private BigDecimal hourlyWage;

  public PartTimeEmployee(String name, BigDecimal hourlyWage) {
    super(name);
    this.hourlyWage = hourlyWage;
  }
}
