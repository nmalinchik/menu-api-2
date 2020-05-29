package ru.mallin.menuapi2.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Measure {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @OneToMany(mappedBy = "measure")
    private Set<Ingredient> ingredients = new HashSet<>();
}
