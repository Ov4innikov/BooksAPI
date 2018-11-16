package ru.booksapi.restcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.booksapi.exceptions.RestException;

/**
 * Contoller for ExceptionHandler
 *
 * @author Eugene Ovchinnikov
 */
@Controller
public class ExceptionHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(RestException.class)
    public @ResponseBody
    String handleException(RestException e) {
        logger.error("Error: " + e.getMessage(), e);
        return "Error: " + e.getMessage();
    }
}