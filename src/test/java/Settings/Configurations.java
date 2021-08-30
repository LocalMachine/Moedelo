package Settings;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

public class Configurations {
    protected WebDriver driver;
    /*
    public void browserConfiguration(String browser, boolean headless) {
        Configuration.browser = browser;
        Configuration.headless = headless;
        Configuration.startMaximized = true;
        Configuration.timeout = 8000;
    }

     */

    public void browserConfiguration(String browser, boolean headless) throws MalformedURLException {
        //Configuration.remote = "http://192.168.0.136:4444/wd/hub";
        //Configuration.driverManagerEnabled = false;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "92.0");
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false
        ));
        driver = new RemoteWebDriver(
                URI.create("http://192.168.0.136:4444/wd/hub").toURL(),
                capabilities
        );

    }

    @BeforeTest
    @Parameters("browser")
    protected void setUp(@Optional("browser") String browser) throws MalformedURLException {
        new Configurations().browserConfiguration(browser, true);
    }

    @AfterMethod
    protected void tearDown() {
        //new Helpers().closeDriver();
    }

}
