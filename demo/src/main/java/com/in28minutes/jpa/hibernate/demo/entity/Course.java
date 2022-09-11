package com.in28minutes.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
// @NamedQuery(name = "query_get_all_courses", query = "Select c from Course c")
// @NamedQuery(name = "query_get_100_Steps_courses", query = "Select c from Course c where name
// like'%100 Steps'")
@NamedQueries({
  @NamedQuery(name = "query_get_all_courses", query = "Select c from Course c"),
  @NamedQuery(
      name = "query_get_100_Steps_courses",
      query = "Select c from Course c where name like'%100 Steps'")
})
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String name;

  @UpdateTimestamp // hibernate annotation not jpa
  private LocalDateTime createdDate;

  @CreationTimestamp // hibernate annotation not jpa
  private LocalDateTime lastUpdatedDate;

  public Course(String name) {
    this.name = name;
  }
}
