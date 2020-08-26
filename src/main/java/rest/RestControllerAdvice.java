package rest;

import exceptions.InvalidPathException;
import exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
public class RestControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String resourceNotFoundHandler(ResourceNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidPathException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidPathHandler(InvalidPathException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String sqlExceptionHandler(SQLException e){
        return e.getMessage();
    }
}
