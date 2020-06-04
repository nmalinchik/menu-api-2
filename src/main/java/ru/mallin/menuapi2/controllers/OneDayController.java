package ru.mallin.menuapi2.controllers;

import org.springframework.web.bind.annotation.*;
import ru.mallin.menuapi2.entity.Dish;
import ru.mallin.menuapi2.entity.OneDay;
import ru.mallin.menuapi2.entity.OneDayForFront;
import ru.mallin.menuapi2.repos.OneDayRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class OneDayController {

    private final OneDayRepo repo;

    public OneDayController(OneDayRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/menu/{from}")
    public List<OneDayForFront> getWeekMenu(@PathVariable String from) {
        LocalDate start = LocalDate.parse(from);
        LocalDate end = start.plusDays(6);

        List<OneDay> all = (List<OneDay>) repo.findAll();
        all = all
                .stream()
                .filter(d -> (d.getDate().isAfter(start) || d.getDate().isEqual(start)) && (d.getDate().isBefore(end) || d.getDate().isEqual(end)))
                .sorted(new Comparator<OneDay>() {
                    @Override
                    public int compare(OneDay oneDay, OneDay t1) {
                        if (oneDay.getDate().isBefore(t1.getDate())) {
                            return-1;
                        } else if (oneDay.getDate().isAfter(t1.getDate())) {
                            return +1;
                        } else {
                            return 0;
                        }
                    }
                })
                .collect(Collectors.toList());

        List<OneDayForFront> result = new ArrayList<>();
        for (OneDay oneDay : all) {
            result.add(parseOneDayForFront(oneDay));
        }
        return result;
    }

    @PostMapping("/menu/add")
    public OneDayForFront saveOneDay(@RequestBody OneDayForFront oneDayForFront) {
        OneDay save = repo.save(parseOneDayForApi(oneDayForFront));
        return parseOneDayForFront(save);
    }

    private OneDay parseOneDayForApi(OneDayForFront oneDayForFront) {
        OneDay oneDay = new OneDay();
        oneDay.setDate(oneDayForFront.getDate());
        List<Dish> dishes = new ArrayList<>();
        dishes.add(oneDayForFront.getBreakfast());
        dishes.addAll(oneDayForFront.getMain());
        dishes.add(oneDayForFront.getExtra());
        oneDay.setDishes(dishes);
        return oneDay;
    }

    private OneDayForFront parseOneDayForFront(OneDay oneDay) {
        OneDayForFront forFront = new OneDayForFront();
        forFront.setId(oneDay.getId());
        forFront.setDate(oneDay.getDate());
        for (Dish dish : oneDay.getDishes()) {
            switch (dish.getCategory().getTitle()) {
                case "Завтрак":
                    forFront.setBreakfast(dish);
                    break;
                case "Суп":
                case "Салат":
                    forFront.setExtra(dish);
                    break;
                case "Основное":
                case "Мясное":
                    forFront.getMain().add(dish);
                    break;
                case "Гарнир":
                    forFront.getMain().add(0, dish);
                    break;
            }
        }
        return forFront;
    }

}
