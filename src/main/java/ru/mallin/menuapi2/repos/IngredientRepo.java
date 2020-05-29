package ru.mallin.menuapi2.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mallin.menuapi2.entity.Ingredient;

public interface IngredientRepo extends CrudRepository<Ingredient, Long> {
}
