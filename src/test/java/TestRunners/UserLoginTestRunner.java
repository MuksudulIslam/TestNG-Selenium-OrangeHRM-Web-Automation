package TestRunners;

import Config.Setup;
import Pages.LoginPages;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserLoginTestRunner extends Setup {
    LoginPages loginPages;

    @Test(priority = 1, groups = "regression", description = "User Login without Required Credentials")
    public void doUserLoginWithoutCreds(){
        loginPages = new LoginPages(driver);
        loginPages.doLogin("","");
        String textActual = driver.findElements(By.tagName("span")).get(0).getText();
        Assert.assertTrue(textActual.contains("Required"));
        Allure.description("User Logged in Unsuccessful");
    }

    @Test(priority = 2, groups = "regression", description = "User Login without Username Credentials")
    public void doUserLoginWithoutUsername() throws IOException, ParseException {
        loginPages = new LoginPages(driver);
        JSONArray empList = Utils.readEmployeList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empList.get(empList.size() - 1);
        String password = (String) empObj.get("password");
        loginPages.doLogin("", password);
        String textActual = driver.findElement(By.tagName("span")).getText();
        Assert.assertTrue(textActual.contains("Required"));
        Allure.description("User Logged in Unsuccessful");
    }

    @Test(priority = 3, groups = "regression", description = "User Login without Password Credentials")
    public void doUserLoginWithoutPass() throws IOException, ParseException, InterruptedException {
        loginPages = new LoginPages(driver);
        driver.navigate().refresh();
        JSONArray empList = Utils.readEmployeList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empList.get(empList.size() - 1);
        String username = (String) empObj.get("username");
        loginPages.doLogin(username, "");
        Thread.sleep(2000);
        String textActual = driver.findElement(By.tagName("span")).getText();
        Thread.sleep(2000);
        Assert.assertTrue(textActual.contains("Required"));
        Allure.description("User Logged in Unsuccessful");
    }


    @Test(priority = 4 , groups = {"regression", "smoke"}, description = "User Login with Valid Credentials")
    public void doUserLogin() throws IOException, ParseException {
        loginPages = new LoginPages(driver);
        driver.navigate().refresh();
        JSONArray empList = Utils.readEmployeList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empList.get(empList.size() - 1);
        String username = (String) empObj.get("username");
        String password = (String) empObj.get("password");
        loginPages.doLogin(username, password);
        String actualName = driver.findElement(By.className("oxd-userdropdown-name")).getText();
        String expectedName = empObj.get("firstName").toString() + " " + empObj.get("lastName").toString();
        Assert.assertTrue(actualName.contains(expectedName));
        Allure.description("User Logged in Successful");
    }



}



