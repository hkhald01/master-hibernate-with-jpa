package com.in28minutes.database.databasedemo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.database.databasedemo.entity.Person;
import com.in28minutes.database.databasedemo.jpa.PersonJpaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

  @Autowired PersonJpaRepository personRepository;

  public static void main(String[] args) {
    SpringApplication.run(JpaDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    log.info("User id 10001 -> {}", personRepository.findById(10001L));
    log.info(
        "Inserting -> {}",
        personRepository.saveOrUpdate(new Person("Tara", "Berlin", LocalDateTime.now())));

    log.info(
        "Inserting -> {}",
        personRepository.saveOrUpdate(
            new Person(10003L, "Pieter", "Utrecht", LocalDateTime.now())));

    personRepository.deleteById(10002L);
  }
}
