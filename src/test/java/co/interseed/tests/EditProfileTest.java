package co.interseed.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class EditProfileTest extends ParentClass {
    String username = "devinterseed";
    @Test
    public void loginTest() {
        driver.get(url);
        checkCurrentUrlToBe(url + "/login");
        driver.findElement(By.id("email")).sendKeys("dev@interseed.co");
        driver.findElement(By.id("password")).sendKeys("asdasdasd");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.urlToBe(url + "/dashboard"));
        checkCurrentUrlToBe(url + "/dashboard");
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void sanityTest() {
        driver.findElement(By.id("profile-dropdown")).click();
        driver.findElement(By.linkText("My Profile")).click();
        checkCurrentUrlToBe(url + "/profile/" + username);

        checkElementIsPresent(By.xpath("//h1[normalize-space()='Interseed Dev']"));

    }
}
