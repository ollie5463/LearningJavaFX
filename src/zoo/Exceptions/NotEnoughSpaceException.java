package zoo.Exceptions;

public class NotEnoughSpaceException extends UIException{
    public NotEnoughSpaceException() {
        super("There is not enough space for the animal");
    }
}
