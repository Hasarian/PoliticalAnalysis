package be.demoustiez.politicalAnalysisAPI.Errors;

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
