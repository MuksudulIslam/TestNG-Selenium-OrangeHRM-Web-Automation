package TestRunners;

import Config.EmoloyeeModel;
import Config.Setup;
import Pages.LoginPages;
import Pages.UserUpdatePage;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class UserUpdateTestRunner extends Setup {

    LoginPages loginPages;
    UserUpdatePage userUpdatePage;

    @BeforeTest(groups = {"regression", "smoke"})
    public void userLogin() throws IOException, ParseException {
        loginPages = new LoginPages(driver);
        userUpdatePage = new UserUpdatePage(driver);
        JSONArray empList = Utils.readEmployeList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empList.get(empList.size()-1);
        String username = (String) empObj.get("username");
        String password = (String) empObj.get("password");
        loginPages.doLogin(username,password);
        userUpdatePage.menuItems.get(2).click();
    }

    @Test(priority = 1, groups = "regression", description = "User Update Gender Information Without Select any Gender")
    public void UserGenderUpdateWithoutSelect() throws InterruptedException {
        userUpdatePage = new UserUpdatePage(driver);
        Thread.sleep(2000);
        Utils.doScroll(driver,0,500);
        Thread.sleep(2000);
        userUpdatePage.doGenderUpdateWithoutSelect();
        Allure.description("Do not Update User Gender Information");
    }

    @Test(priority = 2, groups = "regression", description = "User Update Gender Information With proper Select any Gender")
    public void UserGenderUpdateInfo() throws InterruptedException {
        userUpdatePage = new UserUpdatePage(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);
        Utils.doScroll(driver,0,500);
        Thread.sleep(2000);
        userUpdatePage.doGenderUpdate();
        Thread.sleep(1000);
        String textActual = driver.findElement(By.id("oxd-toaster_1")).getText();
        Assert.assertTrue(textActual.contains("Success"));
        Allure.description("Update User Gender information Successfully");
    }



    @Test(priority = 3, groups = "regression", description = "User Update Blood group Information Without Select any Blood group")
    public void UserBloodGroupUpdateWithoutSelect() throws InterruptedException {
        userUpdatePage = new UserUpdatePage(driver);
        driver.navigate().refresh();
        Utils.doScroll(driver,0,1000);
        Thread.sleep(2000);
        userUpdatePage.doBloodGroupUpdateWithoutSelect();
        Thread.sleep(2000);
        Utils.doScroll(driver,0,500);
        String textActual = driver.findElements(By.className("oxd-select-text-input")).get(2).getText();
        Assert.assertTrue(textActual.contains("Select"));
        Allure.description("Do not Update User Blood Group Information");

    }

    @Test(priority = 4, groups = "regression", description = "User Update Blood Group Information With proper Select any Blood Group")
    public void UserBloodGroupUpdate() throws InterruptedException {

        userUpdatePage = new UserUpdatePage(driver);
        driver.navigate().refresh();
        Utils.doScroll(driver,0,700);
        Thread.sleep(2000);
        userUpdatePage.doBloodGroupUpdate();
        Thread.sleep(2000);
        Utils.doScroll(driver,0,500);
        String textActual = driver.findElements(By.className("oxd-select-text-input")).get(2).getText();
        Assert.assertTrue(textActual.contains("O+"));
        Allure.description("Update User Blood Group Information Successfully");
    }

    @Test(priority = 5, groups ="smoke", description = "User Update Blood Group Information With proper Select any Blood Group")
    public void BloodGroupUpdate() throws InterruptedException {

        userUpdatePage = new UserUpdatePage(driver);
        driver.navigate().refresh();
        Utils.doScroll(driver,0,700);
        Thread.sleep(2000);
        userUpdatePage.bloodGroupUpdate();
        Thread.sleep(2000);
        Utils.doScroll(driver,0,500);
        String textActual = driver.findElements(By.className("oxd-select-text-input")).get(2).getText();
        Assert.assertTrue(textActual.contains("AB-"));
        Allure.description("Update User Blood Group Information Successfully");
    }


    @Test(priority = 6, groups = {"regression", "smoke"}, description = "User Logged Out")
    public void UserLogout(){
        LoginPages loginPages = new LoginPages(driver);
        loginPages.doLogout();
        Allure.description("User Logout");
    }


}
