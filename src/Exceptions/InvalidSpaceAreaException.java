package Exceptions;

public class InvalidSpaceAreaException extends IllegalArgumentException {
    @Override
    public String getMessage(){
        return super.getMessage();
    }
    public InvalidSpaceAreaException(String s) {
        super(s);
    }
}
