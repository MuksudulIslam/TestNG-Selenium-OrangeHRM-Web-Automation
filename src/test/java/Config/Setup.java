package Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class Setup {
    public WebDriver driver;

    @BeforeTest(groups = {"smoke", "regression"})
    public void setup() {
            driver=new EdgeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @AfterTest(groups = {"smoke", "regression"})
    public void quitBrowser() {
        if(driver != null){
            driver.quit();
        }
    }
}
