package ru.mallin.menuapi2.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mallin.menuapi2.entity.Good;
import ru.mallin.menuapi2.entity.Shopping;

import java.util.List;

public interface GoodRepo extends CrudRepository<Good, Long> {

    List<Good> findByShopping(Shopping shopping);

}
