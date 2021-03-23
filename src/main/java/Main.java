import java.net.URL;

public class Main {

    public static final String RECIPE_URL ="https://test.kode-t.ru/recipes.json";

    public static void main(String[] args) {
        // создаем URL из строки
        URL url = JsonUtils.createUrl(RECIPE_URL);

        // загружаем Json в виде Java строки
        String resultJson = JsonUtils.parseUrl(url);

        //System.out.println("Полученный JSON:\n" + resultJson);

        // парсим полученный JSON и печатаем его на экран
        JsonUtils.parseJson(resultJson);


    }
}
