package co.interseed.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class LandscapePageTest extends ParentClass {
    @Test
    public void UploadLandscapeTest() throws InterruptedException {
        // Login to an account
        String slidesUrl = "https://docs.google.com/presentation/d/e/2PACX-1vQqLlGq947Uk94F9J2j0FgMUPvOiGSeN3zhi3zs06OziaTqrnzsg_ln7MLPXURCansGmysTLL-kAfJC/pub?start=false&loop=false&delayms=3000";
        driver.findElement(By.id("email")).sendKeys("dev@interseed.co");
        driver.findElement(By.id("password")).sendKeys("asdasdasd");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.urlToBe(url + "/dashboard"));
        Assert.assertEquals(driver.getCurrentUrl(), url + "/dashboard");

        // Go to Landscapes and upload a new one
        driver.findElement(By.linkText("Landscapes")).click();
        WebElement x = driver.findElement(By.xpath("//button[normalize-space()='Upload']"));
        driver.findElement(By.xpath("//button[normalize-space()='Upload']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), url + "/upload");

        // Fill in the form
        driver.findElement(By.xpath("//input[@id='formTitle']")).sendKeys("Test Landscape");
        driver.findElement(By.xpath("//input[@id='formTagline']")).sendKeys("Testing a feature");
        driver.findElement(By.xpath("//textarea[@id='formDescription']")).sendKeys("Landscape Description");
        driver.findElement(By.xpath("//input[@id='formSlides']")).sendKeys(slidesUrl);
        driver.findElement(By.xpath("//label[normalize-space()='Countries']/following-sibling::div")).click();

        wait.until(ExpectedConditions.textToBe(By.xpath("//*[text()='Cambodia']"), "Cambodia"));
//        driver.findElement(By.xpath("//label[normalize-space()='Countries']/following-sibling::div/div[2]/div/div[3]")).click();
        driver.findElement(By.xpath("//*[text()='Cambodia']")).click();
        Boolean isCountrySelected = false;
        try {
            driver.findElement(By.xpath("//*[text()='Cambodia']"));
            isCountrySelected = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.assertEquals(Boolean.TRUE, isCountrySelected);
//
//        List<WebElement> categories = driver.findElements(By.xpath("//label[normalize-space()='Categories']/following-sibling::div/div[1]/div"));
    }
}
