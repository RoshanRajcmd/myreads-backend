package com.myappliction.springboot_application.user;

import com.myappliction.springboot_application.book.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            Set<Book> bookList1 = new HashSet<>();
            bookList1.add(new Book("Lord of the Rings Vol 1",
                    "An Adventure of Frodo Baggins",
                    LocalDate.of(1954, Month.JULY,29),
                    "John Ronald Reuel Tolkien"));
            User roshan =new User(
                    "Roshan",
                    LocalDate.of(1999, Month.DECEMBER,5),
                    "test@email.com",
                    "password1",
                    new HashSet<Book>(bookList1),
                    new HashSet<Long>());


            User chitra = new User(
                    "Chitra",
                    LocalDate.of(1973, Month.FEBRUARY,12),
                    "chitra@email.com",
                    "password2",
                    new HashSet<Book>(),
                    new HashSet<Long>(Arrays.asList(roshan.getId())));

            repository.saveAll(List.of(roshan, chitra));
        };
    }
}
