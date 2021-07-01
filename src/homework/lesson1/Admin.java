package homework.lesson1;

public class Admin extends Person {
    public Admin(String name, String surname, int age, String email, String password) {
        super(name, surname, age, email, password, Role.ADMIN);
    }
}
