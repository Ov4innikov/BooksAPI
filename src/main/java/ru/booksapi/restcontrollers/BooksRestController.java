package ru.booksapi.restcontrollers;

import java.util.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.booksapi.exceptions.ServiceExeption;
import ru.booksapi.interfaces.BookService;
import ru.booksapi.services.Response;

import javax.servlet.http.HttpServletResponse;

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

    @ApiOperation(value = "Get a list of all books", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "List is empty"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllBooks(HttpServletResponse responseHttp) {
        Map<Integer,Map<String,String>> resultMap = null;
        try{
            resultMap = bookService.getAllBooks();
            return response.successResponse(resultMap);
        } catch (ServiceExeption serviceExeption){
            logger.error("Service error", serviceExeption);
            responseHttp.setStatus(400);
            return response.errorResponse("List is empty");
        } catch (Exception e) {
            logger.error("Error", e);
            responseHttp.setStatus(500);
            return response.errorResponse("Error");
        }
    }

    @ApiOperation(value = "Get a book by id", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Book not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/getBookById/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBooksById(@PathVariable Integer id, HttpServletResponse responseHttp) {
        Map<Integer,Map<String,String>> resultMap = null;
        try{
            resultMap = bookService.getBookById(id);
            return response.successResponse(resultMap);
        } catch (ServiceExeption serviceExeption){
            logger.error("Service error", serviceExeption);
            responseHttp.setStatus(400);
            return response.errorResponse(serviceExeption.getMessage());
        } catch (Exception e) {
            logger.error("Error", e);
            responseHttp.setStatus(500);
            return response.errorResponse("Error");
        }
    }

    @ApiOperation(value = "Get books by author id", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Books not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/getBooksByAuthorId/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBooksByAuthorId(@PathVariable Integer id, HttpServletResponse responseHttp) {
        Map<Integer,Map<String,String>> resultMap = null;
        try{
            resultMap = bookService.getBooksByAuthorId(id);
            return response.successResponse(resultMap);
        } catch (ServiceExeption serviceExeption){
            logger.error("Service error", serviceExeption);
            responseHttp.setStatus(400);
            return response.errorResponse(serviceExeption.getMessage());
        } catch (Exception e) {
            logger.error("Error", e);
            responseHttp.setStatus(500);
            return response.errorResponse("Error");
        }
    }

    @ApiOperation(value = "Get books by genre id", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Books not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/getBooksByGenreId/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBooksByGenreId(@PathVariable Integer id, HttpServletResponse responseHttp) {
        Map<Integer,Map<String,String>> resultMap = null;
        try{
            resultMap = bookService.getBooksByGenreId(id);
            return response.successResponse(resultMap);
        } catch (ServiceExeption serviceExeption){
            logger.error("Service error", serviceExeption);
            responseHttp.setStatus(400);
            return response.errorResponse(serviceExeption.getMessage());
        } catch (Exception e) {
            logger.error("Error", e);
            responseHttp.setStatus(500);
            return response.errorResponse("Error");
        }
    }

    @ApiOperation(value = "Update book by id, example of request body: \n " +
            "{\n" +
            "\t\"id\":\"1\",\n" +
            "\t\"name\":\"Test\",\n" +
            "\t\"series\":\"Series test\",\n" +
            "\t\"countOfPage\":\"100\",\n" +
            "\t\"description\":\"Test description\",\n" +
            "\t\"authorId\":2,\n" +
            "\t\"genreId\":3\n" +
            "}", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Book not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/updateBookById", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateBookById(@RequestBody Map<String,String> updatedBook, HttpServletResponse responseHttp) {
        try{
            bookService.updateBookById(updatedBook);
            logger.debug("GetId=" + updatedBook.get("id") + "; Series=" + updatedBook.get("series"));
            return response.successResponse("OK");
        } catch (ServiceExeption serviceExeption){
            logger.error("Service error", serviceExeption);
            responseHttp.setStatus(400);
            return response.errorResponse(serviceExeption.getMessage());
        } catch (Exception e) {
            logger.error("Error", e);
            responseHttp.setStatus(500);
            return response.errorResponse("Error");
        }
    }

    @ApiOperation(value = "Put new book, example of request body: \n {\n" +
            "\t\"name\":\"TestPut\",\n" +
            "\t\"series\":\"Put test\",\n" +
            "\t\"countOfPage\":23,\n" +
            "\t\"description\":\"Test put\",\n" +
            "\t\"authorId\":1,\n" +
            "\t\"genreId\":1\n" +
            "}", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Book can,t be created"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/putNewBook", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> putNewBook(@RequestBody Map<String,String> newBook, HttpServletResponse responseHttp) {
        try{
            bookService.putNewBook(newBook);
            logger.debug("GetId=" + newBook.get("id") + "; Series=" + newBook.get("series"));
            return response.successResponse("OK");
        } catch (ServiceExeption serviceExeption){
            logger.error("Service error", serviceExeption);
            responseHttp.setStatus(400);
            return response.errorResponse(serviceExeption.getMessage());
        } catch (Exception e) {
            logger.error("Error", e);
            responseHttp.setStatus(500);
            return response.errorResponse("Error");
        }
    }

    @ApiOperation(value = "Delete book, example of request body: \n{\n" +
            "\t\"id\":\"6\",\n" +
            "}", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Book can,t be deleted"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/deleteBookById", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> deleteBookById(@RequestBody Map<String,String> deletedBook, HttpServletResponse responseHttp) {
        try{
            bookService.deleteBookById(deletedBook);
            return response.successResponse("OK");
        } catch (ServiceExeption serviceExeption){
            logger.error("Service error", serviceExeption);
            responseHttp.setStatus(400);
            return response.errorResponse(serviceExeption.getMessage());
        } catch (Exception e) {
            logger.error("Error", e);
            responseHttp.setStatus(500);
            return response.errorResponse("Error");
        }
    }

    //Method for adding a book
    /*@RequestMapping(value = "/createBook", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> createBook(HttpServletResponse responseHttp) {
        bookService.createBook();
        return response.successResponse("Book created");
    }*/
}
