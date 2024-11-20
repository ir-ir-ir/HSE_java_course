import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.StringTokenizer;

public class Person {

    private String name;
    private String age;

    // геттеры и сеттеры
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    // конструктор
    Person (String name, String age){
        this.name = name;
        this.age = age;
    }

    // проверка корректности данных
    void parsingData () throws PersonException {
        // проверка даты рождения
        char[] date = age.toCharArray();

        //нет посторонних символов
        if(!age.matches("[0123456789.]+")) { throw new PersonException();}

        //соответстующая длина и .
        if ((age.length() != 10) || (date[2] != '.') || (date[5] != '.')) {throw new PersonException(); }

        //валидный день, месяц, год
        int day = Integer.parseInt(String.valueOf(date[0]) + String.valueOf(date[1]));
        int mounth = Integer.parseInt(String.valueOf(date[3]) + String.valueOf(date[4]));
        int year = Integer.parseInt(String.valueOf(date[6]) + String.valueOf(date[7]) + String.valueOf(date[8]) + String.valueOf(date[9]));

        // если не можем создать объект, пользователь ввел неверную дату
        try{
            LocalDate birth = LocalDate.of(year, mounth, day);
        }
        catch (DateTimeException obj) {
            System.out.println("Неправильный ввод данных!");
            System.exit(0);
        }

        LocalDate currentDate = LocalDate.now(); // текущая дата
        LocalDate dr = LocalDate.of(year, mounth, day); // дата рождения
        // если дата рождения > текущая дата
        if (dr.isAfter(currentDate))  {throw new PersonException();}

        //проверка имени
        StringTokenizer fio = new StringTokenizer(name);
        if (fio.countTokens() != 3) {throw new PersonException();}
        if(!name.matches("[ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэячсмитьбю ]+")) {throw new PersonException();
        }
    }

    // инициалы и определение пола
    void initials_gender(){
        StringTokenizer fio = new StringTokenizer(name);
        String F = ""; // фамилия
        String I = ""; // имя
        String O = ""; // отчество

        //сохранение фио в отдельные строки
        for (int i = 0; i < 3; i++) {
            String a = fio.nextToken();
            if (i == 0) {
                F = a;
            }
            if (i == 1) {
                I = a;
            }
            if (i == 2) {
                O = a;
            }
        }
        //печать инициалов
        System.out.print(F);
        System.out.print(" ");
        System.out.print(I.charAt(0));
        System.out.print(". ");
        System.out.print(O.charAt(0));
        System.out.println(".");

        if (O.charAt(O.length() - 1) == 'а') {
            System.out.println("Пол: женский");
        }
        else {
            System.out.println("Пол: мужской");
        }
    }

    // определение возраста
    void years(){
        StringTokenizer date = new StringTokenizer(age,".");
        String day = ""; // день рождения
        String born = ""; // год рождения
        String mounth = ""; // месяц рождения
        for (int i = 0; i < 3; i++) {
            String a = date.nextToken();
            if (i == 0) {
                day = a;
            }
            if (i == 1) {
                mounth = a;
            }
            if (i == 2) {
                born = a;
            }
        }

        //вычисление возраста
        LocalDate currentDate = LocalDate.now(); // текущая дата
        LocalDate dr = LocalDate.of(Integer.parseInt(born), Integer.parseInt(mounth), Integer.parseInt(day)); // дата рождения
        int year = currentDate.getYear() - Integer.parseInt(born); // текущий год - год рождения
        LocalDate psevdodr = dr.plusYears(year); // день рождения, месяц рождения, текущий год
        int f = 0;
        // если в этом году еще не было дня рождения, то возраст = текущий год - год рождения - 1 = year - 1
        if (psevdodr.isAfter(currentDate)){
            f = -1;
        }
        System.out.print("Возраст: ");
        year += f;
        System.out.print(year);

        // определение: лет / года
        int last = year%10; // последняя цифра
        int prlast = year%100; // две последних цифры
        if (((prlast > 4) && (prlast < 20)) || (prlast == 0)){
            System.out.println(" лет");
        }
        else {
            if (last == 1){
                System.out.println(" год");
            }
            else {
                if ( last == 2 || last == 3 || last == 4) {
                    System.out.println(" года");
                }
                else {
                    System.out.println(" лет");
                }
            }
        }
    }
}