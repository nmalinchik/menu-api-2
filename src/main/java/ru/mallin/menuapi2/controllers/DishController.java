package ru.mallin.menuapi2.controllers;

import org.springframework.web.bind.annotation.*;
import ru.mallin.menuapi2.entity.Dish;
import ru.mallin.menuapi2.repos.CategoryRepo;
import ru.mallin.menuapi2.repos.DishRepo;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DishController {

    private final DishRepo repo;
    private final CategoryRepo categoryRepo;

    public DishController(DishRepo repo, CategoryRepo categoryRepo) {
        this.repo = repo;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/dishes")
    public Iterable<Dish> getAll(){
        return repo.findAll();
    }

    @GetMapping("/dishes/{category_id}")
    public Iterable<Dish> findByCategory(@PathVariable long category_id){
        return repo.findByCategory(categoryRepo.findById(category_id).orElse(null));
    }

    @GetMapping("/dishes/{id}")
    public Dish findById(@PathVariable long id){
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/dishes/{category_id}/{title}")
    public Iterable<Dish> findByCategory(@PathVariable long category_id, @PathVariable String title){
        List<Dish> searchedDishes = (List<Dish>) repo.findByCategory(categoryRepo.findById(category_id).orElse(null));
        if (title != null && !title.isEmpty()){
           searchedDishes = searchedDishes.stream().filter(dish -> dish.getTitle().toUpperCase().contains(title.toUpperCase())).collect(Collectors.toList());
        }
        return searchedDishes;
    }

    @PostMapping("/dishes/add")
    public Dish saveDish(@RequestBody Dish dish){
        return repo.save(dish);
    }

    @PutMapping("/dishes/{id}")
    public Dish updateDish(@RequestBody Dish newDish, @PathVariable Long id) {
        return repo.findById(id)
                .map(dish -> {
                    dish.setTitle(newDish.getTitle());
                    dish.setCookingTime(newDish.getCookingTime());
                    dish.setDaysCount(newDish.getDaysCount());
                    dish.setRecipe(newDish.getRecipe());
                    dish.setCookedLastTime(newDish.getCookedLastTime());
                    dish.setRecipeUrl(newDish.getRecipeUrl());
                    dish.setIngredients(newDish.getIngredients());
                    return repo.save(dish);
                })
                .orElseGet(() -> {
                    newDish.setId(id);
                    return repo.save(newDish);
                });
    }

    @DeleteMapping("/dishes/{typeId}")
    void deleteDish(@PathVariable Long id) {
        repo.deleteById(id);
    }

}
