import java.io.*;
import java.util.Scanner;
import static java.lang.Integer.parseInt;


public class Main {

    // запрашиваем у пользователя путь, проверяем его корректность
    // считаем количество символов и делаем return массива
    static String[] path () throws IOException
    {

        char[] abc = {'A','B','C','D','E','F','G','H','I','J','K',
                'L','M','N','O','P','Q','R','S','T','U','V',
                'W','X','Y','Z','a','b','c','d','e','f','g',
                'h','i','j','k','l','m','n','o','p','q','r',
                's','t','u','v','w','x','y','z'};
        String [] count = new String [53]; // массив, где будет храниться количество символов
                                           // a по 52 индексу будет лежать путь до файла


        Scanner in = new Scanner(System.in); // открыли поток на запись
        System.out.println("The file 'answer.txt' will be created in the directory with the transferred file!");
        System.out.print("Input a path: ");
        String way = in.nextLine(); // сохранили путь в строчку
        in.close(); // закрываем поток
        File f_read = new File(way);

        //Заполнение массива count
        for (int i = 0; i < 52; i++) {
            count[i] = "0";
        }
        count[52] = way;

        try (FileReader f = new FileReader(way)){ // открываем на чтение
            System.out.println("The file is open for reading!");
            //cчитаем символы
            int ch;
            while ( (ch = f.read()) != -1) // читаем аски-код одного символа
            {
                for (int i = 0; i < 52; i++)
                {
                    if (ch == (int) abc[i]){
                        int pr = parseInt(count[i]) + 1;
                        count[i] = String.valueOf(pr);
                    }
                }
            }
        }
        catch (FileNotFoundException obj) {
            if (!(f_read.isFile())) //файла не существует или передали папку
            {
                System.out.println("Make sure that it is the fail!");
            }
            else if (!(f_read.canRead()))  // нет прав на чтение
            {
                System.out.println("Make sure that you have read permissions!");
            }
            System.exit(0); // завершаем программу, чтобы не создавать файл-ответ
        }
        return count;
    }

    //создание файла-ответа
    static void createAnswer (String[] count) {
        // Будем создавать файл answer рядом с файлом, путь до которого передал пользователь
        // Находим путь до необходимой папки:
        int last_ch = 0; // индекс последнего '\' в пути, введеном пользователем
        for (int i = 0; i < count[52].length(); i++) {
            char ch = count[52].charAt(i);
            if (ch == '\\'){
                last_ch = i;
            }
        }
        StringBuilder way_answer = new StringBuilder(); // путь до файла-ответа
        for (int i = 0; i <= last_ch; i ++){
            way_answer.append(count[52].charAt(i));
        }
        way_answer.append("answer.txt");
        File f = new File(String.valueOf(way_answer));


        char[] abc = {'A','B','C','D','E','F','G','H','I','J','K',
                'L','M','N','O','P','Q','R','S','T','U','V',
                'W','X','Y','Z','a','b','c','d','e','f','g',
                'h','i','j','k','l','m','n','o','p','q','r',
                's','t','u','v','w','x','y','z'};
        // создаем файл (если уже создан, содержимое сотрется) и открываем поток на запись
        try ( FileWriter fp = new FileWriter(f)) {
            System.out.println("File 'answer.txt' is created!");
            // записываем ответы
            for (int i = 0; i < 52; i++)
            {
                fp.append(abc[i]);
                fp.write(": ");
                fp.write(count[i]);
                fp.write('\n');
            }
        }
        catch (IOException obj) {
            System.out.println("Error writing to the 'answer.txt' file");
        }
    }

    //итоговая функция, вызывающая все методы в нужном порядке
    static void apply() {
        //обработка исключения при открытии потока на запись (path)
        try {
            String[] arr = path();
            createAnswer(arr);
        }
        catch (IOException obj)
        {
            System.out.println("An unexpected error. Try again or enter a different path!");
        }
    }

    public static void main(String[] args)  {
        apply();
    }
}