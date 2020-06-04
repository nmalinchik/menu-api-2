package ru.mallin.menuapi2.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mallin.menuapi2.entity.OneDay;

public interface OneDayRepo extends CrudRepository<OneDay, Long> {
}
