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
import ru.booksapi.exceptions.RestException;
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
    private BooksRepository booksRepository;

    @RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllBooks() throws RestException {
        try{
            List<Book> resultList = new ArrayList<Book>();
            for(Book item:booksRepository.findAll()) {
                resultList.add(item);
                logger.debug("Book name is " + item.getName());
            }
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("1","2");
            return response.successResponse("");
        } catch (Exception e) {
            throw new RestException(e);
        }
    }
}