package ru.mallin.menuapi2.entity;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToOne
    @JoinColumn(name = "measure_id")
    private Measure measure;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
}
