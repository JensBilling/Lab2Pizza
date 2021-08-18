package com.example.schoolspring.controllers;

import com.example.schoolspring.entities.Pizza;
import com.example.schoolspring.repositories.PizzaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PizzaController {

    private PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository){
        this.pizzaRepository= pizzaRepository;
    }

    @GetMapping("/pizzas")
    public List<Pizza> pizzas(){
        return pizzaRepository.findAll();
    }

    @GetMapping("/pizzas/{id}")
    public Optional<Pizza> getOnePizza(@PathVariable Long id){
        return pizzaRepository.findById(id);
    }

    @PostMapping("/pizzas")
    public void addPizza(@RequestBody Pizza pizza){
        pizzaRepository.save(pizza);
    }

     @DeleteMapping("/pizzas/{id}")
    public void deletePizza(@PathVariable Long id){
        pizzaRepository.deleteById(id);
     }

     @GetMapping("/pizzas/search")
    public List<Pizza> searchPizza(@RequestParam String name){
        return pizzaRepository.findAllByName(name);
     }

}
