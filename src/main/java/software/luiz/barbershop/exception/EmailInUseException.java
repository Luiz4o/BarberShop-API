package software.luiz.barbershop.exception;

public class EmailInUseException extends  RuntimeException{
    public EmailInUseException(String message){super((message));}
}
