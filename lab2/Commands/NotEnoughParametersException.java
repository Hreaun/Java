package Commands;

public class NotEnoughParametersException extends RuntimeException{
    public NotEnoughParametersException(String msg){
        super(msg);
    }
}
