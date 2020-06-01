package ru.mallin.menuapi2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dishes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private int cookingTime;
    private int daysCount;
    private String recipe;
    private LocalDate cookedLastTime;
    private String recipeUrl;

    @OneToMany(mappedBy = "dish")
    private Set<Ingredient> ingredients = new HashSet<>();


}
