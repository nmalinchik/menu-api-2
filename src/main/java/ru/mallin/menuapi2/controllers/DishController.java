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
    public List<Dish> getAll(){
        return (List<Dish>) repo.findAll();
    }

    @GetMapping("/dishes/by-category/{category_id}")
    public List<Dish> findByCategory(@PathVariable long category_id){
        return (List<Dish>) repo.findByCategory(categoryRepo.findById(category_id).orElse(null));
    }

    @GetMapping("/dishes/by-title/{title}")
    public List<Dish> findByTitle(@PathVariable String title){
        List<Dish> all = (List<Dish>) repo.findAll();
        return all
                .stream()
                .filter(d -> d.getTitle().toUpperCase().contains(title.toUpperCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/dishes/{id}")
    public Dish findById(@PathVariable long id){
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/dishes/by-cat-and-title/{category_id}/{title}")
    public List<Dish> findByCategoryAndByTitle(@PathVariable long category_id, @PathVariable String title){
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
    public void deleteDish(@PathVariable Long id) {
        repo.deleteById(id);
    }

}
