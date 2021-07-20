package co.interseed.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

public class ParentClass {
    WebDriver driver;
    WebDriverWait wait;
    String url;

    @BeforeMethod
    public void SetUpEnvironment() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\kanis\\Documents\\Legit Stuff\\NUS\\YSI SEA\\Java Extras\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-fullscreen");
//        options.addArguments("--headless");
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920x1080");
        this.driver = new ChromeDriver(options);
        this.url = "http://localhost:3000";
        driver.get(url);
        this.wait = new WebDriverWait(driver, 5);
    }

//    @AfterMethod
//    public void ShutDown() {
//        this.driver.close();
//    }

    public void checkCurrentUrlToBe(String expected) {
        Assert.assertEquals(driver.getCurrentUrl(), expected);
    }

    public List<String> getCurrentTabs() {
        return new ArrayList<String>(driver.getWindowHandles());
    }

}
