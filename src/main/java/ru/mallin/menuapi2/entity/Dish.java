package ru.mallin.menuapi2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "dishes")
@Getter
@Setter
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne
    private Category category;
    private int cookingTime;
    private int daysCount;
    private String recipe;
    private LocalDate cookedLastTime;
    private String recipeUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dish")
    @JsonBackReference
    private Set<Ingredient> ingredients = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id == dish.id &&
                cookingTime == dish.cookingTime &&
                daysCount == dish.daysCount &&
                title.equals(dish.title) &&
                category.equals(dish.category) &&
                Objects.equals(recipe, dish.recipe) &&
                Objects.equals(cookedLastTime, dish.cookedLastTime) &&
                Objects.equals(recipeUrl, dish.recipeUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, category, cookingTime, daysCount, recipe, cookedLastTime, recipeUrl);
    }
}
