package com.in28minutes.jpa.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {

  @Id @GeneratedValue private Long id;

  @Column(nullable = false)
  private String name;

  @OneToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  // @JsonBackReference
  // @JsonManagedReference
  private Passport passport;

  @Embedded private Address address;

  @ToString.Exclude
  @ManyToMany
  @JoinTable(
      name = "student_course",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  // @JsonManagedReference
  private List<Course> courses = new ArrayList<>();

  protected Student() {}

  public Student(String name) {
    this.name = name;
  }

  public void addCourse(Course course) {
    this.courses.add(course);
  }

  public void removeCourse(Course course) {
    this.courses.remove(course);
  }
}
