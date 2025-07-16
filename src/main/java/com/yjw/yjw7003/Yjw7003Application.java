package com.yjw.yjw7003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Yjw7003Application {

  public static void main(String[] args) {
    SpringApplication.run(Yjw7003Application.class, args);
  }

}
