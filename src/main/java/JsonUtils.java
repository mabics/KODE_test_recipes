import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class JsonUtils {

    public static String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        // открываем соедиение к указанному URL
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {

            String inputLine;
            // построчно считываем результат в объект StringBuilder
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    // парсим данные
    public static void parseJson(String resultJson) {
        try {
            // конвертируем строку с Json в JSONObject для дальнейшего его парсинга
            JSONObject recipesJsonObject = (JSONObject) JSONValue.parseWithException(resultJson);

            System.out.println("Recipe List:\n");

            // получаем массив элементов для поля weather
            JSONArray recipesArray = (JSONArray) recipesJsonObject.get("recipes");


            for (int i = 0; i < recipesArray.size(); i++) {

                JSONObject recipesData = (JSONObject) recipesArray.get(i);

                System.out.println("№ " + (i + 1) + " Recipe name: " + recipesData.get("name"));
            }

            while (true) {

                Scanner in = new Scanner(System.in);
                System.out.print("\n select the recipe number, to exit number 0 : ");

                int numberRecipes = in.nextInt() - 1;
                if (numberRecipes == -1) {
                    in.close();
                    break;
                }// выход из цикла

                JSONObject recipesData = (JSONObject) recipesArray.get(numberRecipes);

                System.out.println("\nRecipe Details: " + recipesData.get("name") + "\n id: " + recipesData.get("uuid")
                        + "\n url images: " + recipesData.get("images") + "\n lastUpdated: " + recipesData.get("lastUpdated")
                        + "\n instructions: " + recipesData.get("instructions") + "\n difficulty: " + recipesData.get("difficulty"));

            }


        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    // создаем объект URL из указанной в параметре строки
    public static URL createUrl(String link) {
        try {
            return new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
