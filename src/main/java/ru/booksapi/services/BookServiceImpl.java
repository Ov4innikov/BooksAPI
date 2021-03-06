package ru.booksapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.booksapi.entities.Author;
import ru.booksapi.entities.Book;
import ru.booksapi.entities.Desire;
import ru.booksapi.entities.Genre;
import ru.booksapi.exceptions.ServiceExeption;
import ru.booksapi.interfaces.BookService;
import ru.booksapi.repostitories.AuthorsRepository;
import ru.booksapi.repostitories.BooksRepository;
import ru.booksapi.repostitories.DesiresRepository;
import ru.booksapi.repostitories.GenresRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service of booksapi books
 *
 * @author Eugene Ovchinnikov
 */
@Service
public class BookServiceImpl implements BookService{

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private GenresRepository genresRepository;

    @Autowired
    private DesiresRepository desiresRepository;

    @Override
    public Map<Integer,Map<String,String>> getBookById(Integer id) throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        Book book = null;
        if(booksRepository.findById(id).isPresent()){
            book = booksRepository.findById(id).get();
            resultMap.put(id, convertBookToMap(book));
        }else {
            throw new ServiceExeption("Book not found");
        }
        logger.debug("RESULT BOOK: " + book.getName());

        return resultMap;
    }

    @Override
    public Map<Integer,Map<String,String>> getBooksByAuthorId(Integer authorId) throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        if(booksRepository.findAll().iterator().hasNext()==false) throw new ServiceExeption("Empty books map");
        for(Book item:booksRepository.findAll()) {
            if(item.getAuthor().getId() == authorId)resultMap.put(item.getId(), convertBookToMap(item));
        }
        logger.debug("RESULT MAP" + resultMap.toString());
        return resultMap;
    }

    @Override
    public Map<Integer,Map<String,String>> getBooksByGenreId(Integer genreId) throws ServiceExeption {
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        if(booksRepository.findAll().iterator().hasNext()==false) throw new ServiceExeption("Empty books map");
        for(Book item:booksRepository.findAll()) {
            if(item.getGenre().getId() == genreId)resultMap.put(item.getId(), convertBookToMap(item));
        }
        logger.debug("RESULT MAP" + resultMap.toString());
        return resultMap;
    }

    @Override
    public Map<Integer,Map<String,String>> getAllBooks() throws ServiceExeption {
        logger.debug("Start method getAllBooks.");
        Map<Integer,Map<String,String>> resultMap = new HashMap<Integer,Map<String,String>>();
        for(Book item:booksRepository.findAll()) {
            resultMap.put(item.getId(), convertBookToMap(item));
            logger.debug("Book id is " + item.getId());
        }
        logger.debug("RESULT MAP" + resultMap.toString());
        if (resultMap.size() == 0) throw new ServiceExeption("Empty books map");
        return resultMap;
    }

    @Override
    public void updateBookById(Map<String,String> updatingBook) throws ServiceExeption {
        Book book = null;
        Genre genre = null;
        Author author = null;
        logger.debug("updateBookById, id = " + Long.valueOf(updatingBook.get("id")) + "!!!");
        if(booksRepository.findById(Integer.valueOf(updatingBook.get("id"))).isPresent()) {
            book = booksRepository.findById(Integer.valueOf(updatingBook.get("id"))).get();
        }else {
            throw new ServiceExeption("Book not found");
        }
        if(updatingBook.get("genreId")!=null) {

            if(genresRepository.findById(Integer.valueOf(updatingBook.get("genreId"))).isPresent()) {
                genre = genresRepository.findById(Integer.valueOf(updatingBook.get("genreId"))).get();
                book.setGenre(genre);
            }else {
                throw new ServiceExeption("Genre not found");
            }
        }
        if(updatingBook.get("authorId")!=null) {
            if(authorsRepository.findById(Integer.valueOf(updatingBook.get("authorId"))).isPresent()) {
                author = authorsRepository.findById(Integer.valueOf(updatingBook.get("authorId"))).get();
                book.setAuthor(author);
            }else {
                throw new ServiceExeption("Author not found");
            }
        }
        if(updatingBook.get("series")!=null) {
            book.setSeries(updatingBook.get("series"));
        }
        if(booksRepository.findById(Integer.valueOf(updatingBook.get("name"))).isPresent()) {
            book.setName(updatingBook.get("name"));
        }
        if(updatingBook.get("countOfPage")!=null) {
            book.setCountOfPage(Integer.valueOf(updatingBook.get("countOfPage")));
        }
        if(updatingBook.get("description")!=null) {
            book.setDescription(updatingBook.get("description"));
        }
        if(updatingBook.get("cost")!=null) {
            book.setCost(updatingBook.get("cost"));
        }
        booksRepository.save(book);
    }

    @Override
    public void putNewBook(Map<String,String> newBook) throws ServiceExeption {
        Book book = new Book();
        Genre genre = null;
        Author author = null;
        if(newBook.get("genreId")!=null)throw new ServiceExeption("GenreId is empty");
        if(newBook.get("authorId")!=null)throw new ServiceExeption("AuthorId is empty");
        if(genresRepository.findById(Integer.valueOf(newBook.get("genreId"))).isPresent()) {
            genre = genresRepository.findById(Integer.valueOf(newBook.get("genreId"))).get();
        }else {
            throw new ServiceExeption("Genre not found");
        }
        if(authorsRepository.findById(Integer.valueOf(newBook.get("authorId"))).isPresent()) {
            author = authorsRepository.findById(Integer.valueOf(newBook.get("authorId"))).get();
        }else {
            throw new ServiceExeption("Author not found");
        }
        book.setSeries(newBook.get("series"));
        book.setName(newBook.get("name"));
        book.setCountOfPage(Integer.valueOf(newBook.get("countOfPage")));
        book.setDescription(newBook.get("description"));
        book.setCost(newBook.get("cost"));
        book.setGenre(genre);
        book.setAuthor(author);
        booksRepository.save(book);
    }

    @Override
    public void deleteBookById(Map<String, String> deletedBook) throws ServiceExeption {
        if(deletedBook.isEmpty()) throw new ServiceExeption("Empty patameters");
        if(deletedBook.get("id")==null) throw new ServiceExeption("Empty patameters");
        String id = deletedBook.get("id");
        for(Desire item:desiresRepository.findAll()) {
            if(item.getId()==Integer.valueOf(id)) throw new ServiceExeption("Some desire is link with this book!");
        }
        if(genresRepository.findById(Integer.valueOf(deletedBook.get("id"))).isPresent()) {
            booksRepository.deleteById(Integer.valueOf(id));
        }else {
            throw new ServiceExeption("Book not found");
        }


    }

    //Method for adding a book
    public void createBook(){
        Author author = new Author();
        author.setLastName("Author last name");
        author.setFirstName("Author first name");
        author.setDateOfBirthDay(LocalDate.of(1900, 1,1));

        Genre genre = new Genre();
        genre.setGenre("Genre");

        Book book = new Book();
        book.setSeries("series");
        book.setName("Book name");
        book.setDescription("Description");
        book.setCountOfPage(100);
        book.setAuthor(author);
        book.setGenre(genre);

        booksRepository.save(book);
    }

    private Map<String,String> convertBookToMap(Book book){
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("name",book.getName());
        resultMap.put("name",book.getCost());
        resultMap.put("description",book.getDescription());
        resultMap.put("series",book.getSeries());
        resultMap.put("authorFirstName",book.getAuthor().getFirstName());
        resultMap.put("genre",book.getGenre().getGenre());
        return resultMap;
    }
}
