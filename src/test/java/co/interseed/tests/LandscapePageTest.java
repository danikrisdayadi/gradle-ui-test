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
        checkCurrentUrlToBe(url + "/dashboard");

        // Go to Landscapes and upload a new one
        driver.findElement(By.linkText("Landscapes")).click();
        WebElement x = driver.findElement(By.xpath("//button[normalize-space()='Upload']"));
        driver.findElement(By.xpath("//button[normalize-space()='Upload']")).click();
        checkCurrentUrlToBe(url + "/upload");

        // Fill in the form
        driver.findElement(By.xpath("//input[@id='formTitle']")).sendKeys("Test Landscape");
        driver.findElement(By.xpath("//input[@id='formTagline']")).sendKeys("Testing a feature");
        driver.findElement(By.xpath("//textarea[@id='formDescription']")).sendKeys("Landscape Description");
        driver.findElement(By.xpath("//input[@id='formSlides']")).sendKeys(slidesUrl);

        WebElement uploadElement = driver.findElement(By.id("formThumbnail"));
        uploadElement.sendKeys("C:\\Users\\kanis\\Pictures\\interseed_dev_logo.png");

        driver.findElement(By.xpath("//label[normalize-space()='Countries']/following-sibling::div")).click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[text()='Cambodia']"), "Cambodia"));
        driver.findElement(By.xpath("//*[text()='Cambodia']")).click();

        driver.findElement(By.xpath("//label[normalize-space()='Categories']/following-sibling::div/div[1]/div")).click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[text()='Others']"), "Others"));
        driver.findElement(By.xpath("//*[text()='Others']")).click();

        Boolean isCountrySelected = false;
        Boolean isCategoriesSelected = false;
        try {
            driver.findElement(By.xpath("//*[text()='Cambodia']"));
            driver.findElement(By.xpath("//*[text()='Others']"));
            isCountrySelected = true;
            isCategoriesSelected = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.assertEquals(Boolean.TRUE, isCountrySelected);
        Assert.assertEquals(Boolean.TRUE, isCategoriesSelected);

        driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();

        // Check the landscape is working
        wait.until(ExpectedConditions.urlContains("landscape"));
        Assert.assertEquals(Boolean.TRUE, checkElementIsPresent(By.xpath("//span[text()='Cambodia']")));
        Assert.assertEquals(Boolean.TRUE, checkElementIsPresent(By.xpath("//a[normalize-space()='Interseed Dev']")));
        Assert.assertEquals(Boolean.TRUE, checkElementIsPresent(By.xpath("//a[normalize-space()='Access the document']")));
    }
}
