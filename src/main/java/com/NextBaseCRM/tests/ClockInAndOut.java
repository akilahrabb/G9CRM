package com.NextBaseCRM.tests;

import com.NextBaseCRM.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class ClockInAndOut {

    WebDriver driver;
    WebElement enterLogin;
    WebElement enterPassword;
    WebElement loginButton;
    WebElement timeManager;
    WebElement clockInButton;
    WebElement clockOutButton;
    WebElement breakButton;
    WebElement userBlock;
    WebElement logout;

    @BeforeMethod
    public void setupMethod () {
        driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://login2.nextbasecrm.com/login");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        enterLogin = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        enterLogin.sendKeys("hr18@cybertekschool.com");
        enterPassword = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
        enterPassword.sendKeys("UserUser");
        loginButton = driver.findElement(By.xpath("//input[@class='login-btn']"));
        loginButton.click();
    }

    @AfterMethod
    public void tearDownMethod () {
    userBlock = driver.findElement(By.xpath("//div[@onclick='showUserMenu()']"));
    userBlock.click();
    logout = driver.findElement(By.xpath("//span[contains(text(), 'Log out')]"));
    logout.click();

    driver.close();
    }

    @Test
    public void testClockIn () {

    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    timeManager = driver.findElement(By.xpath("//span[@id='timeman-status']"));
    timeManager.click();

    clockInButton = driver.findElement(By.xpath("//span[@class='webform-small-button tm-popup-main-button webform-small-button-accept']"));
    clockInButton.click();

    clockOutButton = driver.findElement(By.xpath("//span[@class='webform-small-button tm-popup-main-button webform-small-button-decline']"));

        if (clockOutButton.isDisplayed()) {
            System.out.println("Employee is clocked in. Verification PASS!");
        } else {
            System.out.println("FAIL!");
        }
    }

    @Test
    public void testClockOut () {
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    timeManager = driver.findElement(By.xpath("//span[@id='timeman-status']"));
    timeManager.click();

    clockOutButton = driver.findElement(By.xpath("//span[@class='webform-small-button tm-popup-main-button webform-small-button-decline']"));
    clockOutButton.click();

    timeManager = driver.findElement(By.xpath("//span[@id='timeman-status']"));
    timeManager.click();
    breakButton = driver.findElement(By.xpath("//span[@class='webform-small-button tm-webform-button-pause']"));

        if (!breakButton.isDisplayed()) {
            System.out.println("BREAK button is no longer displayed. Verification PASS!");
        } else {
            System.out.println("FAIL!");
        }
    }

}
