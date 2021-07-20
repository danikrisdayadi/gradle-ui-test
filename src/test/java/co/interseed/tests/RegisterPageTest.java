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

public class RegisterPageTest extends ParentClass{
    @Test
    public void registerSanityTest() throws InterruptedException {
        driver.get(url + "/register");
        String expectedLoginUrl = url + "/register";
        String loginUrl = driver.getCurrentUrl();
        Assert.assertEquals(loginUrl, expectedLoginUrl);
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Test");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("User");
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("user123");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("user123@gmail.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("superuser");
        driver.findElement(By.xpath("//input[@id='password2']")).sendKeys("superuser");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.textToBe(By.xpath("//small[text()='Username already exists']"), "Username already exists"));
        String expectedUrl = "http://localhost:3000/register";
        String newUrl = driver.getCurrentUrl();
        Boolean hasAccountExisted = false;
        Boolean expectedHasAccountExisted = true;
        try {
            hasAccountExisted = driver.findElement(By.xpath("//small[text()='Username already exists']")).isDisplayed();
        } catch(Exception e) {
            System.out.println("Fail: Element is Not Found [" + e + "]");
        }
        Assert.assertEquals(newUrl, expectedUrl);
        Assert.assertEquals(hasAccountExisted, expectedHasAccountExisted);
    }
}
