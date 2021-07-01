package homework.lesson4.services;

import homework.lesson4.entities.Client;
import homework.lesson4.utils.Helper;

/*5. В папке ‘services’ создаем класс Сервис Транзакций, внутри которого создаем один метод:
Передача Денег. Этот метод на вход принимает 2 параметра (сущность Клиент и Идентификатор Аккаунта клиента) и ничего не возвращает.
Внутри идет вызов созданного Класса-хелпера, метода 3.*/
public class TransactionService {
    public void moneyTransaction(Client client, String clientAccountId) {
        Helper helper = new Helper();
        helper.checkTransactionClientsAccountsId(client.getClientAccountId(), clientAccountId);
    }
}