package com.in28minutes.database.databasedemo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import com.in28minutes.database.databasedemo.entity.Person;
import com.in28minutes.database.databasedemo.jdbc.PersonJdbcDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {

  @Autowired PersonJdbcDao dao;

  public static void main(String[] args) {
    SpringApplication.run(SpringJdbcDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("All users -> {}", dao.findAll());

    log.info("User id 10001 -> {}", dao.findById(10001L));
    log.info("Delete User with id 10002 -> {}", dao.deleteById(10002L));

    log.info(
        "Inserting 10004 -> {}",
        dao.insert(new Person(10004L, "Tara", "Berlin", LocalDateTime.now())));

    log.info(
        "Inserting 10003 -> {}",
        dao.update(new Person(10003L, "Tara", "Utrecht", LocalDateTime.now())));
  }
}
