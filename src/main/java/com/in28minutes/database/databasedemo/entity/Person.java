package com.in28minutes.database.databasedemo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {

  @Id @GeneratedValue private Long id;
  private String name;
  private String location;
  private LocalDateTime birthDate;

  public Person(String name, String location, LocalDateTime birthDate) {
    super();
    this.name = name;
    this.location = location;
    this.birthDate = birthDate;
  }
}
