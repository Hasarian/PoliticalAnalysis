package be.demoustiez.politicalAnalysisAPI.Errors;

public class InvalidParameter extends RuntimeException {
    private String correction;
    public InvalidParameter(String message){this.correction=message; }
    public String getMessage(){
        return correction;
    }
}
