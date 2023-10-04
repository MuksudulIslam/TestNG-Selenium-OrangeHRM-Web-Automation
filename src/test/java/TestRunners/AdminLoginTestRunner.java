package TestRunners;

import Config.Setup;
import Pages.LoginPages;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AdminLoginTestRunner extends Setup {
     LoginPages adminLogin;

     @Test(priority = 1, groups = "regression", description = "Admin Login without Required Credentials")
     public void doLoginWithEmptyField(){
          adminLogin = new LoginPages(driver);
          adminLogin.doLogin("","");
          String textActual = driver.findElement(By.xpath("//div[@class='orangehrm-login-form']//div[2]//div[1]//span[1]")).getText();
          String textExpected  = "Required";
          Assert.assertEquals(textExpected,textActual);
          Allure.description("Admin Login Unsuccessful");

     }

     @Test(priority = 2, groups= "regression", description = "Admin Login with Invalid Credentials")
     public void doLoginWithInvalidCreds(){
          adminLogin = new LoginPages(driver);
          adminLogin.doLogin("wrongadmin","wrongpassword");
          String textActual = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
          String textExpected = "Invalid";
          Assert.assertTrue(textActual.contains(textExpected));
          Allure.description("Admin Login Unsuccessful");
     }

     @Test(priority = 3, groups = { "regression", "smoke"}, description = "Admin Login with Valid Credentials")
     public void doLoginWithValidCreds() throws IOException, ParseException {
          adminLogin = new LoginPages(driver);
          JSONArray empList = Utils.readEmployeList("./src/test/resources/employees.json");
          JSONObject empObj = (JSONObject) empList.get(0);
          String username = (String) empObj.get("username");
          String password = (String) empObj.get("password");
          if(System.getProperty("username") != null && (System.getProperty("password") != null)){
               adminLogin.doLogin(System.getProperty("username"), System.getProperty("password"));
          }
          else{
               adminLogin.doLogin(username, password);
          }
          Assert.assertTrue(driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed());

          Allure.description("Admin Login Successful");
     }


}
