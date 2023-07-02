package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver = Driver.getDriver();

    public BasePage() {
        PageFactory.initElements(driver,this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
}
