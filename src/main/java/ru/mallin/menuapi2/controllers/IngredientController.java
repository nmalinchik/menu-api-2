package ru.mallin.menuapi2.controllers;

import org.springframework.web.bind.annotation.*;
import ru.mallin.menuapi2.entity.Ingredient;
import ru.mallin.menuapi2.repos.IngredientRepo;
import ru.mallin.menuapi2.repos.TypeRepo;

@RestController
public class IngredientController {

    private final IngredientRepo repo;
    private final TypeRepo typeRepo;

    public IngredientController(IngredientRepo repo, TypeRepo typeRepo) {
        this.repo = repo;
        this.typeRepo = typeRepo;
    }

    @GetMapping("/ingredients")
    public Iterable<Ingredient> getAll(){
        return repo.findAll();
    }

    @GetMapping("/ingredients/{typeId}")
    public Iterable<Ingredient> getAllByType(@PathVariable long typeId){
        return repo.findByType(typeRepo.findById(typeId).orElse(null));
    }

    @PostMapping("/ingredients/add")
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient){
        return repo.save(ingredient);
    }

    @PutMapping("/ingredients/{id}")
    public Ingredient replaceIngredient(@RequestBody Ingredient newIngredient, @PathVariable Long id) {
        return repo.findById(id)
                .map(ingredient -> {
                    ingredient.setTitle(newIngredient.getTitle());
                    ingredient.setMeasure(newIngredient.getMeasure());
                    ingredient.setType(newIngredient.getType());
                    ingredient.setDish(newIngredient.getDish());
                    ingredient.setAmount(newIngredient.getAmount());
                    return repo.save(ingredient);
                })
                .orElseGet(() -> {
                    newIngredient.setId(id);
                    return repo.save(newIngredient);
                });
    }

    @DeleteMapping("/ingredients/{typeId}")
    void deleteIngredient(@PathVariable Long id) {
        repo.deleteById(id);
    }


}
