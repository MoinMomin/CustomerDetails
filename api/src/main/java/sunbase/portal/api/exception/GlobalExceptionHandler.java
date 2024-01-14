package sunbase.portal.api.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sunbase.portal.api.exception.customExceptions.UserNotFound;
import sunbase.portal.api.utils.CustomResponse;

import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map> IllegalArgumentExceptions (IllegalArgumentException  illegalArgumentException){
        return CustomResponse.conflict("error due to IllegalArgument  value please check"+illegalArgumentException.getMessage());
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map> IllegalArgumentExceptions (NoSuchElementException noSuchElementException){
        return CustomResponse.conflict("No such Customer with this id");
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Map> EmptyResultDataAccessExceptions (EmptyResultDataAccessException emptyResultDataAccessException){
        return CustomResponse.conflict("No such Customer with this id available in records");
    }
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Map> nullPointerExeception(UserNotFound userNotFound){
        return CustomResponse.ok("user not available for tthis id");
    }
}
