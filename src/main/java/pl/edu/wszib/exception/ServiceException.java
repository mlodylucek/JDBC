package pl.edu.wszib.exception;

public class ServiceException extends RuntimeException {

    private ServiceException(String message) {
        super(message);
    }

    public static ServiceException throwWithMessage(String message){

        return new ServiceException(message);
    }
}
