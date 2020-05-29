package ru.mallin.menuapi2.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mallin.menuapi2.entity.Type;

public interface ProductTypeRepo extends CrudRepository<Type, Long> {
}
