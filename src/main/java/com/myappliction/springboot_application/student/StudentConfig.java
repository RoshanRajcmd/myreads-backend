package com.myappliction.springboot_application.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student roshan =new Student(
                    "Roshan",
                    LocalDate.of(1999, Month.DECEMBER,5),
                    "test@email.com");
            Student chitra = new Student(
                    "Chitra",
                    LocalDate.of(1973, Month.FEBRUARY,12),
                    "chitra@email.com");

            repository.saveAll(List.of(roshan, chitra));
        };
    }
}
