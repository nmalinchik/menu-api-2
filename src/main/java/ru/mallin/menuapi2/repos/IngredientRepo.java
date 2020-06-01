package ru.mallin.menuapi2.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mallin.menuapi2.entity.Ingredient;
import ru.mallin.menuapi2.entity.Type;

public interface IngredientRepo extends CrudRepository<Ingredient, Long> {

    Iterable<Ingredient> findByType(Type type);

}
