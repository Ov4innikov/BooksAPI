package ru.booksapi.repostitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.booksapi.entities.Author;
import ru.booksapi.entities.Book;

/**
 * Repository for opertaion with authores
 *
 * @author Eugene Ovchinnikov
 */
@Repository
public interface AuthorsRepository extends CrudRepository<Author, Long> {

}