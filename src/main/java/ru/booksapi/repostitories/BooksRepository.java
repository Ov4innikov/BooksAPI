package ru.booksapi.repostitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.booksapi.entities.Book;

/**
 * Repository for opertaion with books
 *
 * @author Eugene Ovchinnikov
 */
@Repository
public interface BooksRepository extends CrudRepository<Book, Long> {

}
