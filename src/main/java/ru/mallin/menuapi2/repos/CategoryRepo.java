package ru.mallin.menuapi2.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mallin.menuapi2.entity.Category;

public interface CategoryRepo extends CrudRepository<Category, Long> {
}
