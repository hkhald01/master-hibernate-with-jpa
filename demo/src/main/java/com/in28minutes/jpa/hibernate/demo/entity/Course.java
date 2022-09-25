package com.in28minutes.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@SQLDelete(sql = "update course set is_deleted = true where id - ?")
@Where(clause = "is_deleted = false")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIdentityReference
  private Long id;

  @Column(nullable = false)
  private String name;

  @ToString.Exclude
  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "course") // in Owner Review entity I am  mappedBy course
  // @JsonBackReference
  // @JsonIgnore
  private List<Review> reviews = new ArrayList<>();

  @ToString.Exclude
  // @JsonBackReference
  @ManyToMany(mappedBy = "courses")
  @JsonIgnore
  private List<Student> students =
      new ArrayList<>(); // in the Owner Student entity I am  mappedBy courses

  @UpdateTimestamp // hibernate annotation not jpa
  private LocalDateTime createdDate;

  @CreationTimestamp // hibernate annotation not jpa
  private LocalDateTime lastUpdatedDate;

  private boolean isDeleted;

  public Course(String name) {
    this.name = name;
  }

  public Course(String name, LocalDateTime createdDate, LocalDateTime lastUpdatedDate) {
    this.name = name;
    this.createdDate = createdDate;
    this.lastUpdatedDate = lastUpdatedDate;
    // this.isDeleted = isDeleted;
  }

  public void addReview(Review review) {
    this.reviews.add(review);
  }

  public void removeReview(Review review) {
    this.reviews.remove(review);
  }

  public void addStudent(Student student) {
    this.students.add(student);
  }

  public void removeStudent(Student student) {
    this.students.remove(student);
  }

  @PreRemove
  private void preRemove() {
    log.info("Setting isDeleted");
    this.isDeleted = true;
  }
}
