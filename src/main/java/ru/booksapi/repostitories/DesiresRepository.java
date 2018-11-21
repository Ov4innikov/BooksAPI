package ru.booksapi.repostitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.booksapi.entities.Desire;

/**
 * Repository for opertaion with desires
 *
 * @author Eugene Ovchinnikov
 */
@Repository
public interface DesiresRepository extends CrudRepository<Desire, Integer> {
}
