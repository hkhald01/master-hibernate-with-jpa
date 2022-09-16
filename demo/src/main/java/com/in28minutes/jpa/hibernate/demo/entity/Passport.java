package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Passport {

  @Id @GeneratedValue private Long id;

  @Column(nullable = false)
  private String number;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
  @ToString.Exclude
  // @JsonManagedReference
  private Student student;

  public Passport(String number) {
    this.number = number;
  }
}
