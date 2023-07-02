package stepDefinition;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.Pages;

import java.io.File;
import java.io.IOException;

@Slf4j
public class BaseStep {
    protected Pages pages = new Pages();

    WebDriver driver;

    @BeforeSuite
    public void setUp() {
        String URL = ConfigurationReader.getProperty("url");
        String browser = ConfigurationReader.getProperty("browser");

        Driver.getDriver().get(URL);
        pages.getHOME_PAGE().acceptCookies();

        log.info(":::: Starting test automation ::::");
        log.info("Browser type :: {}",browser);
        log.info("Environment :: {}", URL);

    }

    @AfterSuite
    public void tearDown() throws IOException {
        Driver.getDriver().quit();
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
        FileUtils.copyFile(source, destination);
        return destination.getPath();
    }

}
