package ru.mallin.menuapi2.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mallin.menuapi2.entity.Measure;

public interface MeasureRepo extends CrudRepository<Measure, Long> {
}
