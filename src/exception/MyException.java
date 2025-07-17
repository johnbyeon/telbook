package exception;

public class MyException extends Exception{
    public static final Long serialVersionUID = 1L;
    public MyException(){}
    public MyException(String message){
        super(message);
    }




}
