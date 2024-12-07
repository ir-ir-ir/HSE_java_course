import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    static void connection(String strUrl) {

        System.out.println();
        System.out.println("Response: ");

        try {
            // создание url
            URL url = new URL(strUrl);
            // подключение к ресурсу
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            // получение содержимого ресурса
            try(InputStream os = url.openStream();                 // входной поток байт
                InputStreamReader is = new InputStreamReader(os); // входной поток символов
                BufferedReader br = new BufferedReader(is))       // буфферезированный поток символов
            {
                // печать
                String str;
                while ((str = br.readLine()) != null){
                    if ((str.trim()).equals("},")){ continue; }
                    int f = 0; // индитификатор для печати переноса строки
                    for (int i = 0; i < str.length(); i++){
                        char a = str.charAt(i);
                        if ((a !=  '[') && (a !=  ']') && (a !=  '{') && (a !=  '}') && (a !=  '"')){
                            System.out.print(a);
                            f = 1;
                        }
                    }
                    if (f == 1) {System.out.println();}
                }
            }
            catch (IOException e){
                System.out.println("Не удалось установить соединение с ресурсом!");
            }

            // разрыв соединения
            conn.disconnect();
        }
        catch (IOException e) {
            System.out.println("Не удалось установить соединение с ресурсом!");
        }

        System.out.println("End of the response");
    }

    public static void main(String[] args) {

        String url1 = "https://fake-json-api.mock.beeceptor.com/users";
        String url2 = "https://dummy-json.mock.beeceptor.com/todos";
        connection(url1);
        connection(url2);

    }
}
