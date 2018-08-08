package zoo.Exceptions;

public class UIException extends Exception{
    public UIException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
