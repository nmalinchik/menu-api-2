package ru.mallin.menuapi2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OneDayForFront {

    private long id;

    private LocalDate date;
    private Dish breakfast;
    private List<Dish> main = new ArrayList<>();
    private Dish extra;


}
