package ru.booksapi.restcontrollers;

import java.util.*;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.booksapi.entities.Book;
import ru.booksapi.entities.Genre;
import ru.booksapi.exceptions.RestException;
import ru.booksapi.exceptions.ServiceExeption;
import ru.booksapi.interfaces.BookService;
import ru.booksapi.repostitories.BooksRepository;
import ru.booksapi.services.Response;

/**
 * Contoller for operation with entity book: GET/POST/PUT/DELETE
 *
 * @author Eugene Ovchinnikov
 */
@Controller
@RequestMapping("/book")
@Api(value="booksapi", description="Operations pertaining to books")
public class BooksRestController {

    private static final Logger logger = LoggerFactory.getLogger(BooksRestController.class);

    @Autowired
    private Response response;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllBooks() throws RestException {
        List<Book> listOfBooks = null;
        try{
            listOfBooks = bookService.getAllBooks();
            return response.successResponse(listOfBooks);
        } catch (ServiceExeption serviceExeptione){
            logger.error("Service error", serviceExeptione);
            return response.errorResponse("ServiceError");
        } catch (Exception e) {
            logger.error("Error", e);
            return response.errorResponse("Error");
        }
    }

}
