package ru.mallin.menuapi2.controllers;

import org.springframework.web.bind.annotation.*;
import ru.mallin.menuapi2.entity.*;
import ru.mallin.menuapi2.repos.OneDayRepo;
import ru.mallin.menuapi2.repos.ShoppingListRepo;
import ru.mallin.menuapi2.util.Util;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class ShoppingController {

    private final ShoppingListRepo repo;
    private final OneDayRepo oneDayRepo;

    public ShoppingController(ShoppingListRepo repo, OneDayRepo oneDayRepo) {
        this.repo = repo;
        this.oneDayRepo = oneDayRepo;
    }

    @GetMapping("/shopping/by-id/{id}")
    public Shopping getShoppingById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/shopping/{date}")
    public Shopping getShoppingByDate(@PathVariable String date) {
        LocalDate currentDate = LocalDate.parse(date);
        List<Shopping> all = (List<Shopping>) repo.findAll();
        Optional<Shopping> first = all.stream().filter(shopping -> (shopping.getStartPeriod().isBefore(currentDate) || shopping.getStartPeriod().isEqual(currentDate)) &&
                (shopping.getEndPeriod().isAfter(currentDate) || shopping.getEndPeriod().isEqual(currentDate)))
                .findFirst();

        return first.orElse(null);
    }

    @GetMapping("/shopping/add/{date}")
    public Shopping saveShopping(@PathVariable String date) {
        LocalDate start = LocalDate.parse(date);
        LocalDate end = start.plusDays(6);
        Shopping newShopping = new Shopping();
        newShopping.setStartPeriod(start);
        newShopping.setEndPeriod(end);

        List<OneDay> all = (List<OneDay>) oneDayRepo.findAll();
        all = all.stream()
                .filter(d -> (d.getDate().isAfter(start) || d.getDate().isEqual(start)) && (d.getDate().isBefore(end) || d.getDate().isEqual(end)))
                .collect(Collectors.toList());
        Map<String, Ingredient> ingredientMap = new HashMap<>();
        Set<Dish> dishSet = new HashSet<>();
        for (OneDay oneDay : all) {
            dishSet.addAll(oneDay.getDishes());
        }
        List<Ingredient> allIngredients = new ArrayList<>();

        for (Dish dish : dishSet) {
            allIngredients.addAll(dish.getIngredients());
        }
//
//        Util util = new Util();
//        util.convertIngredientValuesToOneType(allIngredients);

        for (Ingredient ingredient : allIngredients) {
            if (!ingredient.getType().getTitle().equalsIgnoreCase("БЕСПЛАТНОЕ")) {
                ingredientMap.merge(ingredient.getTitle().toLowerCase() + "-" + ingredient.getMeasure().getTitle(), ingredient, (oldVal, newVal) -> {
                    oldVal.setAmount(oldVal.getAmount() + newVal.getAmount());
                    return oldVal;
                });
            }
        }

        Set<Good> goodSet = new HashSet<>();

        for (Ingredient ingredient : ingredientMap.values()) {
            goodSet.add(new Good(ingredient.getTitle(), ingredient.getType().getTitle(), ingredient.getMeasure().getTitle(), ingredient.getAmount(), newShopping));
        }

        newShopping.setGoods(goodSet);
        return repo.save(newShopping);
    }


    @DeleteMapping("/shopping/delete/{id}")
    public void deleteShopping(@PathVariable Long id) {
        repo.deleteById(id);
    }



}
