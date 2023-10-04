package Pages;

import Config.Setup;
import Utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

import java.security.Key;
import java.util.List;

import static Utils.Utils.doScroll;

public class UserUpdatePage {
    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> menuItems;
    @FindBy(className = "oxd-radio-input")
    List<WebElement> radioBtn;
    @FindBy(className = "oxd-select-text-input")
    List<WebElement> dropDown;
    @FindBy(className = "oxd-button--secondary")
    List<WebElement> savebtn;

    public UserUpdatePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doGenderUpdate() throws InterruptedException {

        radioBtn.get(0).click();
        savebtn.get(0).click();
    }

    public void doGenderUpdateWithoutSelect(){

        savebtn.get(0).click();
    }

    public void doBloodGroupUpdateWithoutSelect(){
        dropDown.get(2).click();
        dropDown.get(2).sendKeys(Keys.ENTER);
        savebtn.get(1).click();
    }

    public void doBloodGroupUpdate() throws InterruptedException {

        Thread.sleep(1000);
        dropDown.get(2).click();
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ENTER);
        savebtn.get(1).click();
    }

    public void bloodGroupUpdate() throws InterruptedException {

        Thread.sleep(1000);
        dropDown.get(2).click();
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ARROW_DOWN);
        dropDown.get(2).sendKeys(Keys.ENTER);
        savebtn.get(1).click();
    }
}
