package pl.euvic.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.euvic.exceptions.ApiException;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorMessage> handle(ApiException e, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(e.getTime(),e.getStatus(),e.getError(),e.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
