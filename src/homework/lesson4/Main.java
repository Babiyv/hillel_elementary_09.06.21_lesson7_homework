package homework.lesson4;

import homework.lesson4.entities.Client;
import homework.lesson4.exceptions.WrongFieldException;
import homework.lesson4.exceptions.WrongSumException;
import homework.lesson4.services.TransactionService;
import homework.lesson4.utils.Helper;

import java.util.Arrays;
import java.util.Scanner;

/*6. Далее создаем класс Main (параллельно созданным ранее папкам) и прописываем следующую логику:
6.1. С помощью класса Scanner, который помогает ввести данные с клавиатуры, попросить пользователя ввести Идентификатор Аккаунта клиента.
После чего вызвать Класс-хелпер(метод 1) и передать этот параметр для валидации.
Обработать потенциальную ошибку и в случае ошибки уведомить, что данные введены не корректно и попросить ввести значение снова.
Пока клиент не введет корректное значение, программа должна его запрашивать. (Это поле нашего Клиента: Идентификатор Аккаунта клиента);

6.2. Далее схожим образом попросить пользователя ввести Идентификатор Аккаунта клиента, на который потенциально будет выполняться перевод,
так же провалидировать, отреагировать на ошибку и запрашивать пока данные не будут введены корректно.
(Это Идентификатор Аккаунта клиента, который будет передан далее отдельно);

6.3. И третьим параметром просить ввести сумму перевода, вызвать Класс-хелпер(метод 2) для проверки суммы перевода и передать параметр суммы.
Обработать схожим образом потенциальную ошибку и запрашивать ввести корректное значение пока пользователь не введет необходимое.
(Это поле Клиента: Сумма);

6.4. И финально вызвать ранее созданные класс Сервиса с методом Передачи Денег.
Обрабатывать ошибку не надо, в случае ошибки программа просто должна падать с ранее созданной ошибкой UserExpectedError.
После вызова уведомить об успешном переводе средств.*/
public class Main {
    private static final Scanner SC = new Scanner(System.in);
    private static final Helper HELPER = new Helper(); // сделал отдельным полем как и сканер, чтобы не передавать каждый раз в метод;

    public static void main(String[] args) throws WrongFieldException {
//        6.1
        System.out.print("Please enter your account ID: ");
        String inputSenderAccountId = SC.nextLine();
        inputSenderAccountId = checkAccountIdLength(inputSenderAccountId); // *вынес отдельным методом, чтобы не дублировать код в 6.2

//        6.2
        System.out.print("Please enter recipient account ID: ");
        String inputRecipientAccountId = SC.nextLine();
        inputRecipientAccountId = checkAccountIdLength(inputRecipientAccountId);

//        6.3
        System.out.print("Sum of transaction: ");
        Double sumOfTransaction = SC.nextDouble();
        sumOfTransaction = checkTheSumOfTransaction(sumOfTransaction); // *хоть и не дублируется, но вынес отдельным методом, потому что помню как говорили на паре о том, что код удобнее читать и фиксить когда он разбит на методы;

//        6.4
        Client clientSender = new Client("14", "clientSurname", inputSenderAccountId, sumOfTransaction);
        TransactionService transactionService = new TransactionService();
        transactionService.moneyTransaction(clientSender, inputRecipientAccountId);
        System.out.println("Money transaction is successful!");
        SC.close();
    }

    private static Double checkTheSumOfTransaction(Double sumOfTransaction) {
        boolean notValid = true;
        while (notValid) {
            try {
                notValid = false;
                HELPER.checkTheSum(sumOfTransaction);
            } catch (WrongSumException e) {
                notValid = true;
                e.printStackTrace(); // *Специально оставил два разных варианта как шпаргалку на будущее. Предыдущий вариант писал ручками, а тут автоматом сгенерировало чуть иначе, но вроде тоже самое (только другим цветом);
                System.out.print("Sorry, but you can`t send more than 1000. \nPlease enter sum less than 1000: ");
                sumOfTransaction = SC.nextDouble();
            }
        }
        return sumOfTransaction;
    }

    private static String checkAccountIdLength(String inputClientAccountId) {
        boolean notValid = true;
        while (notValid) {
            try {
                notValid = false;
                HELPER.checkClientAccountIdSize(inputClientAccountId);
            } catch (WrongFieldException wfe) {
                notValid = true;
                System.out.println(wfe);
                System.out.println(Arrays.toString(wfe.getStackTrace())); // .getStackTrace() - отображает более глубинно и подробно откуда вылазит ошибка;
                System.out.print("Please enter correct your account ID. It must contain 10 symbols: ");
                inputClientAccountId = SC.nextLine();
            }
        }
        return inputClientAccountId;
    }
}