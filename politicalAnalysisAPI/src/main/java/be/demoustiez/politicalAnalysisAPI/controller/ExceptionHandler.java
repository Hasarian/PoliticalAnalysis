package be.demoustiez.politicalAnalysisAPI.controller;

import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO resourceNotFound(ResourceNotFound thrown){
            return  new ExceptionDTO(thrown);
    }
    public class ExceptionDTO{
        private String message;

        public ExceptionDTO(Exception exception){
            this.message=exception.getMessage();
        }

        public String getMessage() {
            return message;
        }
    }
}
