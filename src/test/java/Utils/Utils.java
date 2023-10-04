package Utils;

import Config.EmoloyeeModel;
import Config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    public static void saveEmployeesInfo(EmoloyeeModel model) throws IOException, ParseException {

        String file = "./src/test/resources/employees.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(file));
        JSONObject empObj = new JSONObject();
        empObj.put("firstName", model.getFirstName());
        empObj.put("lastName", model.getLastName());
        empObj.put("empId", model.getEmpId());
        empObj.put("username", model.getUsername());
        empObj.put("password", model.getPassword());

        jsonArray.add(empObj);

        FileWriter writer = new FileWriter(file);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();

    }
    public static void doScroll(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(" + x + "," + y + ")");
    }


    public static JSONArray readEmployeList(String filename) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(filename));
        return jsonArray;
    }

    public static String randomPass(){
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%&*";

        StringBuilder password = new StringBuilder();
        password.append(upperCase.charAt((int) (Math.random() * upperCase.length())));
        password.append(lowerCase.charAt((int) (Math.random() * lowerCase.length())));
        password.append(numbers.charAt((int) (Math.random() * numbers.length())));
        password.append(symbols.charAt((int) (Math.random() * symbols.length())));

        int desiredLength = 12;
        while (password.length() < desiredLength) {
            String allCharacters = upperCase + lowerCase + numbers + symbols;
            password.append(allCharacters.charAt((int) (Math.random() * allCharacters.length())));
        }

        return password.toString();
    }

    public static int generateRandomNumber(int min, int max) {
        int empId = (int) Math.floor(Math.random() * (max - min) + min);
        return empId;
    }



}
