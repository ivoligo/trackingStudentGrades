package exception;

public class FileServiceException extends RuntimeException{


    public FileServiceException(String errMessage, Throwable cause) {
        super(errMessage, cause);
    }
}
