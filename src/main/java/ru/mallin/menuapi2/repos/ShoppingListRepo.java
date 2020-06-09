package ru.mallin.menuapi2.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mallin.menuapi2.entity.Shopping;

public interface ShoppingListRepo extends CrudRepository<Shopping, Long> {
}
