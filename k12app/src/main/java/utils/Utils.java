package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.User;
import model.UserData;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


public class Utils {

    public static void forceWait(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(String keyUser) {
        Map<String, UserData> output ;
        JsonReader getLocalJsonFile;

        User user = new User();
        try {
            getLocalJsonFile = new JsonReader(new FileReader(Constants.JSON_PATH));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Type mapTokenType = new TypeToken<Map<String, UserData>>() {
        }.getType();

        output= new Gson().fromJson(getLocalJsonFile, mapTokenType);

        return  new User(keyUser,
                output.get(keyUser));

    }

    public static String generateRandomAlphaString(int length){
        return
                RandomStringUtils.randomAlphabetic(length);
    }

    public static String generateRandomNumericString(int length){
        return
                RandomStringUtils.random(length, false, true);
    }

    public static String generateRandomEmail(int length){
        String tail = "@testingmail.com";
        return
                generateRandomAlphaString(12).concat(tail);
    }

    public static Integer generateRandomInteger(int min, int max){
        return  (int)(Math.random() * (max - min + 1)) + min;
    }

    public static String generateRandomPass(int n){
        int qLetters = n / 2;
        int qNumbers = n - qLetters;
        return generateRandomAlphaString(qLetters).concat(generateRandomNumericString(qNumbers)).concat("-*");
     }

    public static WebElement getWebElementFromList(List<WebElement> list, String data){
        return  list.stream()
                .filter(e -> e.getText().equalsIgnoreCase(data))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

