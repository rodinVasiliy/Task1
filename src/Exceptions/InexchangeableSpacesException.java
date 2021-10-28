package Exceptions;

public class InexchangeableSpacesException extends Exception{
    @Override
    public String getMessage(){
        return super.getMessage();
    }
    public InexchangeableSpacesException(String s) {
        super(s);
    }
}
