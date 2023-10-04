package TestRunners;

import Config.EmoloyeeModel;
import Config.Setup;
import Pages.PMIDashboardPage;
import Pages.LoginPages;
import Utils.Utils;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PIMTestRunner extends Setup {

    LoginPages adminLogin;

    @BeforeTest(groups = {"regression", "smoke"})
    public void login() throws IOException, ParseException {

        adminLogin = new LoginPages(driver);
        JSONArray empList = Utils.readEmployeList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empList.get(0);
        String username = (String) empObj.get("username");
        String password = (String) empObj.get("password");
        if(System.getProperty("username") != null && (System.getProperty("password") != null)){
            adminLogin.doLogin(System.getProperty("username"), System.getProperty("password"));
        }
        else {
            adminLogin.doLogin(username, password);
        }
    }

    @Test(priority = 1, groups = {"regression", "smoke"}, description = "Click on PIM Menu")
    public void PMImenu(){
        PMIDashboardPage dashboardPage = new PMIDashboardPage(driver);
        dashboardPage.menuItems.get(1).click();
        Allure.description("Click on PIM Menu Successful");
    }

    @Test(priority = 2 , groups = "regression", description = "Admin creates an User with Empty information")
    public void createUserEmptyInfo() throws InterruptedException {
        PMIDashboardPage pmiDashboardPage = new PMIDashboardPage(driver);
        pmiDashboardPage.createUserEmpty();
        String textActual = driver.findElement(By.xpath("//div[@class='oxd-input-group']//div[1]//span[1]")).getText();
        String textExpected = "Required";
        Assert.assertTrue(textActual.contains(textExpected));
        Allure.description("Employee Account Creation Unsuccessful");

    }


    @Test(priority = 3, groups = "regression", description = "Admin creates an User without given Username and Password")
    public void createUserWithoutPassword() throws InterruptedException {
        PMIDashboardPage adminDashboard = new PMIDashboardPage(driver);
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String empId = String.valueOf(Utils.generateRandomNumber(1111,9999));

        EmoloyeeModel model = new EmoloyeeModel();
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setEmpId(empId);

        adminDashboard.createUserWithoutPass(model);
        String textActual = driver.findElements(By.tagName("p")).get(4).getText();
        String textExpected = "Required";
        Assert.assertTrue(textActual.contains(textExpected));
        Allure.description("Employee Account Creation Unsuccessful");

    }



    @Test(priority = 4, groups = "regression", description = "Admin creates an User account with valid information in required fields")
    public void CreateUser() throws IOException, ParseException, InterruptedException {
        PMIDashboardPage adminDashboard = new PMIDashboardPage(driver);
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String empId = String.valueOf(Utils.generateRandomNumber(1111,9999));
        String username = faker.name().username();
        String password = Utils.randomPass();

        EmoloyeeModel model = new EmoloyeeModel();
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setEmpId(empId);
        model.setUsername(username);
        model.setPassword(password);
        adminDashboard.createUser(model);
        Thread.sleep(1000);
        String textActual = driver.findElements(By.className("orangehrm-tabs-item")).get(0).getText();
        String textExpected = "Personal Details";
        if(textExpected.contains(textActual)) {
            Utils.saveEmployeesInfo(model);
        }
        Allure.description("Employee Account Creation Successful");
    }

    @Test(priority = 5, groups = "regression", description = "Admin Search User by ID with User's name")
    public void SearchByWrongIdText() throws InterruptedException {
        PMIDashboardPage pmiDashboardPage = new PMIDashboardPage(driver);
        driver.navigate().refresh();
        String empId = "name";
        PMImenu();
        pmiDashboardPage.searchByEmpID(empId);
        Thread.sleep(1000);
        Utils.doScroll(driver,0,600);
        Allure.description("User Not Found");
    }

    @Test(priority = 6, groups = "regression", description = "Admin Search User by ID with Empty field.")
    public void SearchByEmptyId() throws InterruptedException {
        PMIDashboardPage pmiDashboardPage = new PMIDashboardPage(driver);
        String empId = "";
        pmiDashboardPage.searchByEmpID(empId);
        Allure.description("User Not Found");
    }


    @Test(priority = 7, groups = {"regression", "smoke"}, description = "Admin Search User by ID with Valid User's ID")
    public void SearchEmpById() throws IOException, ParseException, InterruptedException {
        PMIDashboardPage adminDashboard = new PMIDashboardPage(driver);
        driver.navigate().refresh();
        PMImenu();
        JSONArray empList = Utils.readEmployeList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empList.get(empList.size()-1);
        String empId = (String) empObj.get("empId");
        adminDashboard.searchByEmpID(empId);
        Utils.doScroll(driver,0,400);
        Thread.sleep(2000);
        String textActual = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/span[1]")).getText();
        String textExpected = "Found";
        Assert.assertTrue(textActual.contains(textExpected));
        Allure.description("User Found");

    }

    @Test(priority = 8, groups = "regression", description = "Admin Search User by Name with Empty field.")
    public void SearchEmpByEmptyName() throws InterruptedException {
        PMIDashboardPage pmiDashboardPage = new PMIDashboardPage(driver);
        String firstname = "";
        pmiDashboardPage.searchByEmpName(firstname);
        Allure.description("User Not Found");

    }

    @Test(priority = 9, groups = "regression", description = "Admin Search User by Name with Wrong User's Name")
    public void SearchEmpByWrongName() throws InterruptedException {
        PMIDashboardPage pmiDashboardPage = new PMIDashboardPage(driver);
        String firstname = "WrongName";
        pmiDashboardPage.searchByEmpName(firstname);
        Thread.sleep(1000);
        String textActual = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/span[1]")).getText();
        Thread.sleep(1000);
        String textExpected = "Invalid";
        Assert.assertTrue(textActual.contains(textExpected));
        Allure.description("User Not Found");

    }

    @Test(priority = 10, groups = "regression", description = "Admin Search User by Name with Valid User's Name")
    public void SearchEmpByName() throws IOException, ParseException, InterruptedException {
        PMIDashboardPage adminDashboard = new PMIDashboardPage(driver);
        JSONArray empList = Utils.readEmployeList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empList.get(empList.size()-1);
        String firstName = (String) empObj.get("firstName");
        adminDashboard.searchByEmpName(firstName);
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Profile Picture']")).isDisplayed());
        Allure.description("User Found");

    }

    @Test(priority = 11, groups = {"regression", "smoke"}, description = "Admin Logged out ")
    public void AdminLogout(){
        LoginPages adminLogin = new LoginPages(driver);
        adminLogin.doLogout();
        driver.quit();
        Allure.description("Admin Logout");
    }


}
