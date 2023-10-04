package Pages;

import Config.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPages{
    @FindBy(name = "username")
    WebElement txtUsername;

    @FindBy(name = "password")
    WebElement txtPassword;
    @FindBy(tagName = "button")
    WebElement loginButton;
    @FindBy(className = "oxd-userdropdown-name")
    WebElement userProfileButton;
    @FindBy(className = "oxd-userdropdown-link")
    List<WebElement> linkSubItems;

    public LoginPages(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doLogin(String username, String password) {
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        loginButton.click();
    }

    public void doLogout() {
        userProfileButton.click();
        linkSubItems.get(3).click();
    }


}
