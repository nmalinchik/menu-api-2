package ru.mallin.menuapi2.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String image;

    @OneToMany(mappedBy = "category")
    private Set<Dish> dishes = new HashSet<>();
}
