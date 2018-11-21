package ru.booksapi.restcontrollers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.booksapi.exceptions.ServiceExeption;
import ru.booksapi.interfaces.GenreService;
import ru.booksapi.services.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Contoller for operation with entity genre: GET/POST/PUT/DELETE
 *
 * @author Eugene Ovchinnikov
 */
@Controller
@RequestMapping("/genre")
@Api(value="genressapi", description="Operations pertaining to genres")
public class GenresRestController {

    private static final Logger logger = LoggerFactory.getLogger(GenresRestController.class);

    @Autowired
    private Response response;

    @Autowired
    private GenreService genreService;

    @ApiOperation(value = "Get a list of all genres", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "List is empty"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/getAllGenres", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllAuthors(HttpServletResponse responseHttp) {
        Map<Integer,Map<String,String>> resultMap = null;
        try{
            resultMap = genreService.getAllGenres();
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
    Map<String, Object> getAuthorById(@PathVariable Long id, HttpServletResponse responseHttp) {
        Map<Integer,Map<String,String>> resultMap = null;
        try{
            resultMap = genreService.getGenreById(id);
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

}
