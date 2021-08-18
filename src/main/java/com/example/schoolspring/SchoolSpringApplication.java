package com.example.schoolspring;

import com.example.schoolspring.entities.Pizza;
import com.example.schoolspring.repositories.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolSpringApplication.class, args);
    }


    @Bean
    public CommandLineRunner init(PizzaRepository pizzaRepository){
        return (args) -> {
            if (pizzaRepository.count() == 0) {
                pizzaRepository.save(new Pizza(1L, "Hawaii", "Ost, Tomatsås, Skinka, Ananas", 80));
            }

        };

    }


}
