package Exceptions;

public class InexchangeableFloorsException extends Exception{
    @Override
    public String getMessage(){
        return super.getMessage();
    }
    public InexchangeableFloorsException(String s) {
        super(s);
    }
}
