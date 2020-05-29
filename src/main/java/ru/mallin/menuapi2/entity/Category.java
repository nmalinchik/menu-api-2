package ru.mallin.menuapi2.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String image;

    @OneToMany(mappedBy = "category")
    private Set<Dish> dishes = new HashSet<>();
}
