package com.myappliction.springboot_application.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User roshan =new User(
                    "Roshan",
                    LocalDate.of(1999, Month.DECEMBER,5),
                    "test@email.com");
            User chitra = new User(
                    "Chitra",
                    LocalDate.of(1973, Month.FEBRUARY,12),
                    "chitra@email.com");

            repository.saveAll(List.of(roshan, chitra));
        };
    }
}
