package be.demoustiez.politicalAnalysisAPI.Errors;

public class ArgumentError extends Exception {
    String message;

    public ArgumentError(String argument,String reason){
        this.message=String.format("arg %s is not correct because %s",argument,reason);
    }
    public String getMessage(){
        return this.message;
    }
}
