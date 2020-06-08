package ru.mallin.menuapi2.controllers;

import org.springframework.web.bind.annotation.*;
import ru.mallin.menuapi2.entity.Dish;
import ru.mallin.menuapi2.entity.Ingredient;
import ru.mallin.menuapi2.entity.OneDay;
import ru.mallin.menuapi2.repos.IngredientRepo;
import ru.mallin.menuapi2.repos.OneDayRepo;
import ru.mallin.menuapi2.repos.TypeRepo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class IngredientController {

    private final IngredientRepo repo;
    private final TypeRepo typeRepo;
    private final OneDayRepo oneDayRepo;

    public IngredientController(IngredientRepo repo, TypeRepo typeRepo, OneDayRepo oneDayRepo) {
        this.repo = repo;
        this.typeRepo = typeRepo;
        this.oneDayRepo = oneDayRepo;
    }

    @GetMapping("/ingredients")
    public List<Ingredient> getAll() {
        return (List<Ingredient>) repo.findAll();
    }

    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredientById(@PathVariable long id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/ingredients/by-type/{typeId}")
    public List<Ingredient> getAllByType(@PathVariable long typeId) {
        return repo.findByType(typeRepo.findById(typeId).orElse(null));
    }

    @GetMapping("/ingredients/by-dish/{dishId}")
    public List<Ingredient> getAllByDishId(@PathVariable long dishId) {
        List<Ingredient> all = (List<Ingredient>) repo.findAll();
        List<Ingredient> collect = all
                .stream()
                .filter(ingredient -> ingredient.getDish().getId() == dishId)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        return collect;
    }

    @GetMapping("/ingredients/for-shopping-list/{from}")
    public List<Ingredient> getShoppingList(@PathVariable String from) {
        LocalDate start = LocalDate.parse(from);
        LocalDate end = start.plusDays(6);
        List<OneDay> all = (List<OneDay>) oneDayRepo.findAll();
        all = all.stream()
                .filter(d -> (d.getDate().isAfter(start) || d.getDate().isEqual(start)) && (d.getDate().isBefore(end) || d.getDate().isEqual(end)))
                .collect(Collectors.toList());
        Map<String, Ingredient> ingredientMap = new HashMap<>();
        Set<Dish> dishSet = new HashSet<>();
        for (OneDay oneDay : all) {
            dishSet.addAll(oneDay.getDishes());
        }
        for (Dish dish : dishSet) {
            for (Ingredient ingredient : dish.getIngredients()) {
                if (!ingredient.getType().getTitle().equalsIgnoreCase("БЕСПЛАТНО")) {
                    ingredientMap.merge(ingredient.getTitle().toLowerCase() + "-" + ingredient.getMeasure().getTitle(), ingredient, (oldVal, newVal) -> {
                        oldVal.setAmount(oldVal.getAmount() + newVal.getAmount());
                        return oldVal;
                    });
                }
            }
        }
        return new ArrayList<>(ingredientMap.values());
    }

    @PostMapping("/ingredients/add")
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return repo.save(ingredient);
    }

    @PutMapping("/ingredients/{id}")
    public Ingredient replaceIngredient(@RequestBody Ingredient newIngredient, @PathVariable Long id) {
        return repo.findById(id)
                .map(ingredient -> {
                    ingredient.setTitle(newIngredient.getTitle());
                    ingredient.setMeasure(newIngredient.getMeasure());
                    ingredient.setType(newIngredient.getType());
                    ingredient.setAmount(newIngredient.getAmount());
                    return repo.save(ingredient);
                })
                .orElseGet(() -> {
                    newIngredient.setId(id);
                    return repo.save(newIngredient);
                });
    }

    @DeleteMapping("/ingredients/delete/{id}")
    public Map<String, Boolean> deleteIngredient(@PathVariable Long id) {
        repo.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
