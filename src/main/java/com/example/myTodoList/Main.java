package com.example.myTodoList;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main  {

    @Autowired
    private Flyway flyway;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @PostConstruct
    public void runFlywayMigration() {
        flyway.migrate();
    }
}