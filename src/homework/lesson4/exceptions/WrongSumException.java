package homework.lesson4.exceptions;

/*3.3. WrongSumException(должна быть Checked);*/
public class WrongSumException extends Exception {
    public WrongSumException() {
        super("Checked WrongSumException");
    }
}