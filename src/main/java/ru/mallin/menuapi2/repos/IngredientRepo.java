package ru.mallin.menuapi2.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mallin.menuapi2.entity.Dish;
import ru.mallin.menuapi2.entity.Ingredient;
import ru.mallin.menuapi2.entity.Type;

import java.util.List;

public interface IngredientRepo extends CrudRepository<Ingredient, Long> {

    List<Ingredient> findByType(Type type);
    List<Ingredient> findByDish(Dish dish);

}
