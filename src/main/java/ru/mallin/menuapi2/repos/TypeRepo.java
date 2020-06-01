package ru.mallin.menuapi2.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mallin.menuapi2.entity.Type;

public interface TypeRepo extends CrudRepository<Type, Long> {
}
