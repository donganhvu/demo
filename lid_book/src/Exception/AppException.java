package Exception;


public class AppException extends Exception {

    public AppException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return "Exeption has found at: " + super.getMessage();
    }
}
