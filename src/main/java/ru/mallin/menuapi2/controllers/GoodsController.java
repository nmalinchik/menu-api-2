package ru.mallin.menuapi2.controllers;

import org.springframework.web.bind.annotation.*;
import ru.mallin.menuapi2.entity.Good;
import ru.mallin.menuapi2.entity.Shopping;
import ru.mallin.menuapi2.repos.GoodRepo;
import ru.mallin.menuapi2.repos.ShoppingListRepo;

import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin("*")
public class GoodsController {

    private final GoodRepo repo;
    private final ShoppingListRepo shoppingRepo;

    public GoodsController(GoodRepo repo, ShoppingListRepo shoppingRepo) {
        this.repo = repo;
        this.shoppingRepo = shoppingRepo;
    }

    @GetMapping("/goods/{id}")
    public Good getGoodById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }


    @GetMapping("/goods/by-date/{date}")
    public List<Good> getGoodsByDate(@PathVariable String date) {
        LocalDate currentDate = LocalDate.parse(date);
        List<Shopping> all = (List<Shopping>) shoppingRepo.findAll();
        Optional<Shopping> first = all.stream().filter(shopping -> (shopping.getStartPeriod().isBefore(currentDate) || shopping.getStartPeriod().isEqual(currentDate)) &&
                (shopping.getEndPeriod().isAfter(currentDate) || shopping.getEndPeriod().isEqual(currentDate)))
                .findFirst();
        List<Good> notSortedList = repo.findByShopping(first.get());
        List<Good> sortedList = new ArrayList<>();
        Map<String, List<Good>> map = new HashMap<>();
        map.put("ЯЙЦО", new ArrayList<>());
        map.put("МОЛОЧНОЕ", new ArrayList<>());
        map.put("МЯСО", new ArrayList<>());
        map.put("МЯСО_ПТИЦЫ", new ArrayList<>());
        map.put("ФРУКТЫ", new ArrayList<>());
        map.put("ОВОЩИ", new ArrayList<>());
        map.put("СУХОФРУТЫ_ОРЕХИ", new ArrayList<>());
        map.put("КОНСЕРВЫ", new ArrayList<>());
        map.put("МАСЛО", new ArrayList<>());
        map.put("СЫР", new ArrayList<>());
        map.put("СОУС", new ArrayList<>());
        map.put("РЫБА", new ArrayList<>());
        map.put("МОРЕПРОДУКТЫ", new ArrayList<>());
        map.put("ЗАМОРОЗКА", new ArrayList<>());
        map.put("СПЕЦИИ", new ArrayList<>());
        map.put("КРУПЫ_МУКА", new ArrayList<>());
        map.put("СЛАДОСТИ", new ArrayList<>());
        map.put("МУЧНОЕ", new ArrayList<>());
        map.put("НАПИТКИ", new ArrayList<>());
        map.put("ДРУГОЕ", new ArrayList<>());
        for (Good good : notSortedList){
            map.get(good.getType()).add(good);
        }
        for (List<Good> list : map.values()) {
            list.sort(Comparator.comparing(Good::getTitle));
        }
        sortedList.addAll(map.get("ЯЙЦО"));
        sortedList.addAll(map.get("МОЛОЧНОЕ"));
        sortedList.addAll(map.get("МЯСО"));
        sortedList.addAll(map.get("МЯСО_ПТИЦЫ"));
        sortedList.addAll(map.get("ФРУКТЫ"));
        sortedList.addAll(map.get("ОВОЩИ"));
        sortedList.addAll(map.get("СУХОФРУТЫ_ОРЕХИ"));
        sortedList.addAll(map.get("КОНСЕРВЫ"));
        sortedList.addAll(map.get("МАСЛО"));
        sortedList.addAll(map.get("СЫР"));
        sortedList.addAll(map.get("СОУС"));
        sortedList.addAll(map.get("РЫБА"));
        sortedList.addAll(map.get("МОРЕПРОДУКТЫ"));
        sortedList.addAll(map.get("ЗАМОРОЗКА"));
        sortedList.addAll(map.get("СПЕЦИИ"));
        sortedList.addAll(map.get("КРУПЫ_МУКА"));
        sortedList.addAll(map.get("СЛАДОСТИ"));
        sortedList.addAll(map.get("МУЧНОЕ"));
        sortedList.addAll(map.get("НАПИТКИ"));
        sortedList.addAll(map.get("ДРУГОЕ"));
        return sortedList;
    }

    @PostMapping("/goods/add")
    public Good saveGood(@RequestBody Good good) {
        return repo.save(good);
    }

    @PutMapping("/goods/update/{id}")
    public Good updateGood(@RequestBody Good newGood, @PathVariable Long id) {
        return repo.findById(id)
                .map(good -> {
                    good.setTitle(newGood.getTitle());
                    good.setMeasure(newGood.getMeasure());
                    good.setType(newGood.getType());
                    good.setAmount(newGood.getAmount());
                    return repo.save(good);
                })
                .orElseGet(() -> {
                    newGood.setId(id);
                    return repo.save(newGood);
                });
    }

    @DeleteMapping("/goods/delete/{id}")
    public Map<String, Boolean> deleteGood(@PathVariable Long id) {
        repo.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
