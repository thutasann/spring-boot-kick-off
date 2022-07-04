package com.example.demo.student;

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
        return arg -> {
            Student victor = new Student(
                    "Victor",
                    "victor@gmail.com",
                    LocalDate.of(2002, Month.FEBRUARY, 4)
            );
            Student james = new Student(
                    "james",
                    "james@gmail.com",
                    LocalDate.of(2006, Month.FEBRUARY, 4)
            );

            repository.saveAll(
                    List.of(victor, james)
            );
        };
    }
}
