package co.interseed.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class FooterComponentTest extends ParentClass {

    @Test
    public void sanityTest() {
        driver.get(url);
        String expectedUrl = url + "/login";
        checkCurrentUrlToBe(expectedUrl);

        driver.findElement(By.xpath("//a[normalize-space()='Interseed']"));
        driver.findElement(By.linkText("Privacy Policy"));
    }

    @Test
    public void functionalTest() {
        // Initialise browserTabs to get all the opened windows
        List<String> browserTabs;

        driver.get(url);
        checkCurrentUrlToBe(url + "/login");

        driver.findElement(By.linkText("Privacy Policy"));

        driver.findElement(By.xpath("//a[@href='https://www.facebook.com/interseed.co']")).click();
        Assert.assertEquals(getCurrentTabs().size(), 2);
        driver.switchTo().window(getCurrentTabs().get(1));
        checkCurrentUrlToBe("https://www.facebook.com/interseed.co");
        driver.close();
        driver.switchTo().window(getCurrentTabs().get(0));
        checkCurrentUrlToBe(url + "/login");

        driver.findElement(By.xpath("//a[@href='https://www.instagram.com/interseed.co/']")).click();
        Assert.assertEquals(getCurrentTabs().size(), 2);
        driver.switchTo().window(getCurrentTabs().get(1));
        wait.until(ExpectedConditions.urlToBe("https://www.instagram.com/accounts/login/"));
        driver.close();
        driver.switchTo().window(getCurrentTabs().get(0));
        checkCurrentUrlToBe(url + "/login");

        driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/company/68030389/']")).click();
        Assert.assertEquals(getCurrentTabs().size(), 2);
        driver.switchTo().window(getCurrentTabs().get(1));
        driver.close();
        driver.switchTo().window(getCurrentTabs().get(0));
        checkCurrentUrlToBe(url + "/login");

        driver.findElement(By.xpath("//a[@href='https://open.spotify.com/show/6juG5jtUa4s9tFDokZ0uJ9?si=WVTG9SjwSFGP6RqW4yQvrA']")).click();
        Assert.assertEquals(getCurrentTabs().size(), 2);
        driver.switchTo().window(getCurrentTabs().get(1));
        wait.until(ExpectedConditions.titleIs("Impact In Sight (by Interseed.co) | Podcast on Spotify"));
        driver.close();
        driver.switchTo().window(getCurrentTabs().get(0));

        driver.findElement(By.linkText("Give Feedback")).click();
        Assert.assertEquals(getCurrentTabs().size(), 2);
        driver.switchTo().window(getCurrentTabs().get(1));
        wait.until(ExpectedConditions.titleIs("User Feedback Form [2-3 mins]"));
        driver.close();
        driver.switchTo().window(getCurrentTabs().get(0));

        driver.findElement(By.partialLinkText("Report a Bug")).click();
        Assert.assertEquals(getCurrentTabs().size(), 2);
        driver.switchTo().window(getCurrentTabs().get(1));
        wait.until(ExpectedConditions.titleIs("Bug Report Form"));
        driver.close();
        driver.switchTo().window(getCurrentTabs().get(0));
    }

}
