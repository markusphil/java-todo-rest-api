package exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resName){
        super(resName + " Not Found");
    }
}
