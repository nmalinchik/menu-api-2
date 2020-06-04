package ru.mallin.menuapi2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OneDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;

    @ManyToMany
    @JoinTable (name="oneday_dishes",
            joinColumns=@JoinColumn (name="one_day_id"),
            inverseJoinColumns=@JoinColumn(name="dishes_id"))
    @JsonBackReference
    private List<Dish> dishes;

}
