import java.util.Scanner;

public class Main {

    // обработка данных, введенных пользователем
    static Person gettingData(){
        Scanner in = new Scanner(System.in); // открыли поток на запись
        System.out.print("Введите ФИО в формате Иванов Иван Иванович: ");
        String fio = in.nextLine();
        //System.out.println();
        System.out.print("Введите дату рождения в формате __.__.____ : ");
        String date = in.nextLine();
        in.close(); // закрываем поток
        Person user = new Person (fio,date);

        // проверка корректности данных
        try {
           user.parsingData();
        }
        catch (PersonException obj) {
            System.out.println("Неправильный ввод данных!");
            System.exit(0);
        }
        return user;
    }

    static void apply (){
        Person person = gettingData();
        person.initialsGender();
        person.years();
    }

    public static void main(String[] args) {
        apply();
    }
}