package be.demoustiez.politicalAnalysisAPI.Errors;

public class ResourceNotFound extends Exception {
    private String resourceName;
    public ResourceNotFound(String resourceName){
        this.resourceName=resourceName;
    }

    @Override
    public String getMessage() {
        return this.resourceName + " was not found";
    }
}
