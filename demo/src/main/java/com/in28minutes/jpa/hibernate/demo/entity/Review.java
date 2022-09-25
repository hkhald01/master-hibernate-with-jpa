package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Review {

  @Id @GeneratedValue private Long id;

  @Enumerated(value = EnumType.STRING)
  private ReviewRating rating;

  private String description;
  @ToString.Exclude @ManyToOne // @JsonBackReference
  private Course course;

  public Review(ReviewRating rating, String description) {
    this.rating = rating;
    this.description = description;
  }
}
