package homework.lesson4.exceptions;

/*3.1. UserExpectedError(должна быть Unchecked);*/
public class UserExpectedError extends RuntimeException {
    public UserExpectedError() {
        super("Unchecked UserExpectedError");
    }
}