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
import ru.booksapi.interfaces.DesireService;
import ru.booksapi.services.DesireServiceImpl;
import ru.booksapi.services.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Contoller for operation with entity desire: GET/POST/PUT/DELETE
 *
 * @author Eugene Ovchinnikov
 */
@Controller
@RequestMapping("/desire")
@Api(value="desiresapi", description="Operations pertaining to desires")
public class DesireRestController {

    private static final Logger logger = LoggerFactory.getLogger(DesireRestController.class);

    @Autowired
    private Response response;

    @Autowired
    private DesireService desireService;

    @ApiOperation(value = "Get a list of all desires", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "List is empty"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/getAllDesires", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllDesires(HttpServletResponse responseHttp) {
        Map<Integer,Map<String,String>> resultMap = null;
        try{
            resultMap = desireService.getAllDesires();
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

    @ApiOperation(value = "Get a desire by id", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Desire not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/getDesireById/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDesireById(@PathVariable Integer id, HttpServletResponse responseHttp) {
        Map<Integer,Map<String,String>> resultMap = null;
        try{
            resultMap = desireService.getDesireById(id);
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

    @ApiOperation(value = "Get a desire by userId", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Desire not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/getDesiresByUserId/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDesiresByUserId(@PathVariable Integer id, HttpServletResponse responseHttp) {
        Map<Integer,Map<String,String>> resultMap = null;
        try{
            resultMap = desireService.getDesiresByUserId("");
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

    @ApiOperation(value = "Update desire by id, example of request body: \n ", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Desire not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/updateDesireById", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateDesireById(@RequestBody Map<String,String> updatedDesire, HttpServletResponse responseHttp) {
        try{
            desireService.updateDesireById(updatedDesire);
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

    @ApiOperation(value = "Put new desire, example of request body: {\n" +
            "\t\"userId\": \"User id\",\n" +
            "\t\"bookId\": \"14\"\n" +
            "}", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Desire can,t be created"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/putNewDesire", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> putNewDesire(@RequestBody Map<String,String> newDesire, HttpServletResponse responseHttp) {
        try{
            desireService.putNewDesire(newDesire);
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

    @ApiOperation(value = "Delete desire, example of request body: ", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully operations"),
            @ApiResponse(code = 400, message = "Author can,t be deleted"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/deleteDesireById", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> deleteDesireById(@RequestBody Map<String,String> deletedDesire, HttpServletResponse responseHttp) {
        try{
            desireService.deleteDesireById(deletedDesire);
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
    //Method for adding a desire
    /*@RequestMapping(value = "/createDesire", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> createBook(HttpServletResponse responseHttp) {
        ((DesireServiceImpl) desireService).createDesire();
        return response.successResponse("Desire created");
    }*/
}
