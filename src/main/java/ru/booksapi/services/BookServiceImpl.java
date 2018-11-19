package ru.booksapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.booksapi.entities.Book;
import ru.booksapi.exceptions.ServiceExeption;
import ru.booksapi.interfaces.BookService;
import ru.booksapi.repostitories.BooksRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service of books books
 *
 * @author Eugene Ovchinnikov
 */
@Service
public class BookServiceImpl implements BookService{

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public Book getBookById() {
        return null;
    }

    @Override
    public List<Book> getBooksByAuthor() {
        return null;
    }

    @Override
    public List<Book> getBooksByGenre() {
        return null;
    }

    @Override
    public List<Book> getAllBooks() throws ServiceExeption {
        logger.debug("Start method getAllBooks.");
        List<Book> resultList = new ArrayList<Book>();
        for(Book item:booksRepository.findAll()) {
            resultList.add(item);
            logger.debug("Book name is " + item.getName());
        }
        if (resultList == null) throw new ServiceExeption("Empty books list");
        return resultList;
    }
}
