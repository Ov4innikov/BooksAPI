package ru.booksapi.restcontrollers;

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
import ru.booksapi.interfaces.AuthorService;
import ru.booksapi.services.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Contoller for operation with entity author: GET/POST/PUT/DELETE
 *
 * @author Eugene Ovchinnikov
 */
@Controller
@RequestMapping("/author")
@Api(value="authorsapi", description="Operations pertaining to authors")
public class AuthorsRestController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorsRestController.class);

    @Autowired
    private Response response;

    @Autowired
    private AuthorService authorService;

    @ApiOperation(value = "Get a list of all authors", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "List is empty"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/getAllAuthors", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllAuthors(HttpServletResponse responseHttp) {
        Map<Integer,Map<String,String>> resultMap = null;
        try{
            resultMap = authorService.getAllAuthors();
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

    @ApiOperation(value = "Get a author by id", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Author not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/getAuthorById/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAuthorById(@PathVariable Integer id, HttpServletResponse responseHttp) {
        Map<Integer,Map<String,String>> resultMap = null;
        try{
            resultMap = authorService.getAuthorById(id);
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

    @ApiOperation(value = "Update author by id, example of request body: \n {\n" +
            "\t\"id\":3,\n" +
            "\t\"firstName\":\" Test John\"\n" +
            "}", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Author not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/updateAuthorById", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateAuthorById(@RequestBody Map<String,String> updatedAuthor, HttpServletResponse responseHttp) {
        try{
            authorService.updateAuthorById(updatedAuthor);
            logger.debug("GetId=" + updatedAuthor.get("id") + "; Series=" + updatedAuthor.get("series"));
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

    @ApiOperation(value = "Put new author, example of request body: \n {\n" +
            "\t\"dateOfBirthDay\":\"1999-10-10\",\n" +
            "\t\"firstName\":\"John\",\n" +
            "\t\"lastName\":\"WiFi\"\n" +
            "}", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Author can,t be created"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/putNewAuthor", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> putNewAuthor(@RequestBody Map<String,String> newAuthor, HttpServletResponse responseHttp) {
        try{
            authorService.putNewAuthor(newAuthor);
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

    @ApiOperation(value = "Delete author, example of request body: ", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Author can,t be deleted"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/deleteAuthorById", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> deleteAuthorById(@RequestBody Map<String,String> deletedAuthor, HttpServletResponse responseHttp) {
        try{
            authorService.deleteAuthorById(deletedAuthor);
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
}
