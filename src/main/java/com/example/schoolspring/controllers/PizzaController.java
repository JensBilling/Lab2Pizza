package com.example.schoolspring.controllers;

import com.example.schoolspring.entities.Pizza;
import com.example.schoolspring.repositories.PizzaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PizzaController {

    private PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/pizzas")
    public List<Pizza> pizzas() {
        return pizzaRepository.findAll();
    }

    @GetMapping("/pizzas/{id}")
    public Optional<Pizza> getOnePizza(@PathVariable Long id) {
        return pizzaRepository.findById(id);
    }

    @PostMapping("/pizzas")
    public void addPizza(@RequestBody Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    @DeleteMapping("/pizzas/{id}")
    public void deletePizza(@PathVariable Long id) {
        pizzaRepository.deleteById(id);
    }

    @GetMapping("/pizzas/search")
    public List<Pizza> searchPizza(@RequestParam String name) {
        return pizzaRepository.findAllByName(name);
    }

    @PutMapping("/pizzas/{id}")
    public void updatePizza(@PathVariable Long id, @RequestBody Pizza pizza) {
        Pizza updatedPizza = new Pizza(id, pizza.getName(), pizza.getIngredients(), pizza.getPrice());
        pizzaRepository.save(updatedPizza);
    }

    @PatchMapping("/pizzas/{id}")
    public void patchPizzaName(@PathVariable Long id, @RequestBody Pizza pizza) {
        Optional<Pizza> oldPizza = pizzaRepository.findById(id);
        Pizza updatedPizza = new Pizza();
        if (oldPizza.equals(null)) {
            return;
        }
        if (oldPizza.get().getName().equals(pizza.getName()) ||  pizza.getName() == null) {
            updatedPizza.setName(oldPizza.get().getName());
        } else {
            updatedPizza.setName(pizza.getName());
        }
        if (oldPizza.get().getIngredients().equals(pizza.getIngredients()) ||  pizza.getIngredients() == null) {
            updatedPizza.setIngredients(oldPizza.get().getIngredients());
        } else {
            updatedPizza.setIngredients(pizza.getIngredients());
        }
        if (oldPizza.get().getPrice() == (pizza.getPrice()) ||  pizza.getPrice() == 0) {
            updatedPizza.setPrice(oldPizza.get().getPrice());
        } else {
            updatedPizza.setPrice(pizza.getPrice());
        }
        updatedPizza.setId(id);
        pizzaRepository.save(updatedPizza);
    }

}
