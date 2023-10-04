package Pages;

import Config.EmoloyeeModel;
import Utils.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.Key;
import java.util.List;

public class PMIDashboardPage {
    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> menuItems;

    @FindBy(className = "oxd-button")
    public List<WebElement> buttons;
    @FindBy(className = "oxd-input")
    public List<WebElement> formTextFields;
    @FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
    public WebElement btnSwitch;
    @FindBy(className = "oxd-input")
    public List<WebElement> inputField;
    @FindBy(className = "oxd-button")
    public List<WebElement> buttonList;
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    public WebElement inputName;


    public PMIDashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createUserEmpty() throws InterruptedException {
        buttons.get(2).click();
        Thread.sleep(2000);
        formTextFields.get(1).click();
        buttons.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    public void createUserWithoutPass(EmoloyeeModel model) throws InterruptedException {
        formTextFields.get(1).sendKeys(model.getFirstName());
        formTextFields.get(3).sendKeys(model.getLastName());
        formTextFields.get(4).sendKeys(Keys.CONTROL + "a");
        formTextFields.get(4).sendKeys(Keys.BACK_SPACE);
        formTextFields.get(4).sendKeys(model.getEmpId());
        btnSwitch.click();
        buttons.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
    }
    public void createUser(EmoloyeeModel model) throws InterruptedException {

        buttons.get(0).sendKeys(Keys.ENTER);
        buttons.get(2).click();
        formTextFields.get(1).sendKeys(model.getFirstName());
        formTextFields.get(3).sendKeys(model.getLastName());
        formTextFields.get(4).sendKeys(Keys.CONTROL + "a");
        formTextFields.get(4).sendKeys(Keys.BACK_SPACE);
        formTextFields.get(4).sendKeys(model.getEmpId());
        btnSwitch.click();

        formTextFields.get(5).sendKeys(model.getUsername());
        formTextFields.get(6).sendKeys(model.getPassword());
        formTextFields.get(7).sendKeys(model.getPassword());
        Thread.sleep(1000);
        buttons.get(1).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
    }



    public void searchByEmpID(String empId) {

        inputField.get(1).sendKeys(empId);
        buttonList.get(1).click();
    }

    public void searchByEmpName(String firstName) throws InterruptedException {

        menuItems.get(8).click();
        inputName.sendKeys(firstName);
        inputName.sendKeys(Keys.SPACE);
        Thread.sleep(2000);
        inputName.sendKeys(Keys.ARROW_DOWN);
        inputName.sendKeys(Keys.ENTER);
        buttonList.get(1).click();


    }




}
