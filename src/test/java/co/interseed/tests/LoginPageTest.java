package co.interseed.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends ParentClass {
        @Test
        public void LoginTest() throws InterruptedException {
            driver.findElement(By.id("email")).sendKeys("dev@interseed.co");
            driver.findElement(By.id("password")).sendKeys("asdasdasd");
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            wait.until(ExpectedConditions.urlToBe(url + "/dashboard"));
            String expectedLoginUrl = url + "/dashboard";
            String loginUrl = driver.getCurrentUrl();
            Assert.assertEquals(loginUrl, expectedLoginUrl);
        }

}
