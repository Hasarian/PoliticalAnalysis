package be.demoustiez.politicalAnalysisAPI.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFound extends RuntimeException {
    private String resourceName;
    public ResourceNotFound(String resourceName){
        this.resourceName=resourceName;
    }

    @Override
    public String getMessage() {
        return this.resourceName + " was not found";
    }
}
