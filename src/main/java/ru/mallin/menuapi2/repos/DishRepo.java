package ru.mallin.menuapi2.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mallin.menuapi2.entity.Dish;

public interface DishRepo extends CrudRepository<Dish, Long> {
}
