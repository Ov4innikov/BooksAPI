package ru.booksapi.restcontrollers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.booksapi.exceptions.ServiceExeption;
import ru.booksapi.interfaces.AuthorService;
import ru.booksapi.interfaces.BookService;
import ru.booksapi.services.AuthorServiceImpl;
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
@Api(value="authorapi", description="Operations pertaining to authors")
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
    Map<String, Object> getAllAuthor(HttpServletResponse responseHttp) {
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
}
