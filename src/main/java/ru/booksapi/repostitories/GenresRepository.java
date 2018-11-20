package ru.booksapi.repostitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.booksapi.entities.Book;
import ru.booksapi.entities.Genre;

/**
 * Repository for opertaion with genres
 *
 * @author Eugene Ovchinnikov
 */
@Repository
public interface GenresRepository extends CrudRepository<Genre, Long> {

}