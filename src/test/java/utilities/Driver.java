package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private Driver(){}

    public synchronized static WebDriver getDriver(){

        if (driverPool.get() == null){
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();

            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized").addArguments("--remote-allow-origins=*");
                    driverPool.set(new ChromeDriver(options));
                   break;
                case "chromeheadless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setHeadless(true);
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--start-maximized").addArguments("--remote-allow-origins=*");
                    driverPool.set(new FirefoxDriver());
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized").addArguments("--remote-allow-origins=*");
                    driverPool.set(new EdgeDriver(edgeOptions));
                    break;
            }
        }
        return driverPool.get();
    }
    public static void closeDriver(){
        if (driverPool != null){
            driverPool.get().quit();
            driverPool.remove();
        }
    }

}
