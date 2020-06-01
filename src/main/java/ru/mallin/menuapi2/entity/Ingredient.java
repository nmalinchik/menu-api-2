package ru.mallin.menuapi2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @ManyToOne
    private Type type;
    @ManyToOne
    private Measure measure;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
}
