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
    String slidesUrl = "https://docs.google.com/presentation/d/e/2PACX-1vQqLlGq947Uk94F9J2j0FgMUPvOiGSeN3zhi3zs06OziaTqrnzsg_ln7MLPXURCansGmysTLL-kAfJC/pub?start=false&loop=false&delayms";
    String country = "Cambodia";
    String categories = "Others";
    String description = "Landscape Description";
    String tagline = "Testing a Feature";
    String title = "Test Landscape";

    @Test
    public void LoginTest() throws InterruptedException {
        driver.get(url);
        checkCurrentUrlToBe(url + "/login");
        driver.findElement(By.id("email")).sendKeys("dev@interseed.co");
        driver.findElement(By.id("password")).sendKeys("asdasdasd");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.urlToBe(url + "/dashboard"));
        checkCurrentUrlToBe(url + "/dashboard");
    }

    @Test(dependsOnMethods = {"LoginTest"})
    public void UploadLandscapeTest() throws InterruptedException {
        // Go to Landscapes and upload a new one
        driver.findElement(By.linkText("Landscapes")).click();
        WebElement x = driver.findElement(By.xpath("//button[normalize-space()='Upload']"));
        driver.findElement(By.xpath("//button[normalize-space()='Upload']")).click();
        checkCurrentUrlToBe(url + "/upload");

        // Fill in the form
        driver.findElement(By.xpath("//input[@id='formTitle']")).sendKeys(title);
        driver.findElement(By.xpath("//input[@id='formTagline']")).sendKeys(tagline);
        driver.findElement(By.xpath("//textarea[@id='formDescription']")).sendKeys(description);
        driver.findElement(By.xpath("//input[@id='formSlides']")).sendKeys(slidesUrl);

        WebElement uploadElement = driver.findElement(By.id("formThumbnail"));
        uploadElement.sendKeys("C:\\Users\\kanis\\Pictures\\interseed_dev_logo.png");

        driver.findElement(By.xpath("//label[normalize-space()='Countries']/following-sibling::div")).click();
        wait.until(ExpectedConditions.textToBe(By.xpath(String.format("//*[text()='%s']", country)), country));
        driver.findElement(By.xpath(String.format("//*[text()='%s']", country))).click();

        driver.findElement(By.xpath("//label[normalize-space()='Categories']/following-sibling::div/div[1]/div")).click();
        wait.until(ExpectedConditions.textToBe(By.xpath(String.format("//*[text()='%s']", categories)), categories));
        driver.findElement(By.xpath(String.format("//*[text()='%s']", categories))).click();

        Boolean isCountrySelected = false;
        Boolean isCategoriesSelected = false;
        try {
            driver.findElement(By.xpath(String.format("//*[text()='%s']", country)));
            driver.findElement(By.xpath(String.format("//*[text()='%s']", categories)));
            isCountrySelected = true;
            isCategoriesSelected = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.assertEquals(Boolean.TRUE, isCountrySelected);
        Assert.assertEquals(Boolean.TRUE, isCategoriesSelected);

        driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();

        // Basic check ensuring the landscape is generated
        wait.until(ExpectedConditions.urlContains("landscape"));
        checkElementIsPresent(By.xpath("//a[normalize-space()='Interseed Dev']"));
        checkElementIsPresent(By.xpath("//a[normalize-space()='Access the document']"));
    }

    @Test(dependsOnMethods = {"UploadLandscapeTest"})
    public void LandscapeSanityCheck() {
        driver.findElement(By.linkText("Landscapes")).click();
        checkCurrentUrlToBe(url + "/landscapes");
        driver.findElement(By.xpath(String.format("//h2[normalize-space()='%s']", title))).click();

        // Check landscape details
        checkElementIsPresent(By.xpath("//a[normalize-space()='Interseed Dev']"));
        checkElementIsPresent(By.xpath("//a[normalize-space()='Access the document']"));
        checkElementIsPresent(By.xpath(String.format("//p[normalize-space()='%s']", description)));

        String modalHeadersLocation = "//div[@class='modal-header']/div[2]";
        String currTagline = driver.findElement(By.xpath(modalHeadersLocation + "/p")).getText();
        String currTitle = driver.findElement(By.xpath(modalHeadersLocation + "/h2[1]")).getText();
        String currCategories = driver.findElement(By.xpath(modalHeadersLocation + "/h2[2]")).getText();
        Assert.assertEquals(currTagline, tagline);
        Assert.assertEquals(currTitle, title);
        Assert.assertEquals(currCategories, categories);

        checkElementIsPresent(By.xpath("//a[normalize-space()='Edit Landscape']"));
        checkElementIsPresent(By.xpath("//button[normalize-space()='Delete Landscape']"));
    }
}
